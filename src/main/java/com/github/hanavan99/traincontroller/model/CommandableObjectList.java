package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.rabbitmq.client.Channel;

public abstract class CommandableObjectList {
    private final Map<String, CommandableObject> list;
    private final Channel channel;
    private final CommandQueue cmd;

    public abstract String getObjectType();
    public abstract CommandableObject create(String name) throws IOException;
    public abstract CommandableObject create(Map<String, String> properties);

    public CommandQueue getCommandBase() {
        return cmd;
    }

    public Channel getChannel() {
        return channel;
    }

    public void sendUpdates() throws IOException {
        //channel.basicPublish(String.format(TopicNames.OBJECT_LIST, getObjectType()), "", null, String.join("\n", getNames()).getBytes("UTF-8"));
    }

    public void add(String name) throws IOException {
        CommandableObject obj = create(name);
        list.put(name, obj);
        obj.registerAll();
        sendUpdates();
    }

    public boolean exists(String name) {
        return list.containsKey(name);
    }

    public void rename(String oldName, String newName) throws IOException {
        CommandableObject value = list.remove(oldName);
        value.unregisterAll();
        list.put(newName, value);
        value.registerAll();
        sendUpdates();
    }

    public void delete(CommandableObject element) throws IOException {
        list.remove(element.getName()).unregisterAll();
        sendUpdates();
    }

    public String[] getNames() {
        return list.keySet().toArray(new String[0]);
    }

    public CommandableObject[] getValues() {
        return list.values().toArray(new CommandableObject[0]);
    }

    public void load(List<Map<String, String>> data) throws IOException {
        list.clear();
        for (Map<String, String> entity : data) {
            CommandableObject obj = create(entity);
            list.put(obj.getName(), obj);
            obj.registerAll();
        }
    }

    public List<Map<String, String>> save() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for (CommandableObject obj : getValues()) {
            data.add(obj.properties);
        }
        return data;
    }

    public CommandableObjectList(Channel channel, CommandQueue cmd) throws IOException {
        list = new HashMap<String, CommandableObject>();
        new NewObjectConsumer(channel, this);
        //new ListObjectConsumer(channel, this);
        this.channel = channel;
        this.cmd = cmd;
    }
}
