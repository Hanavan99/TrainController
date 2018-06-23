package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CommandableObject {
    private static final Logger log = LogManager.getLogger();
    private final Map<String, String> properties;
    private final List<TopicConsumer> subscriptions;
    private final Channel channel;
    private final CommandQueue cmd;
    final CommandableObjectList list;

    public Channel getChannel() {
        return channel;
    }

    public CommandQueue getCommandBase() {
        return cmd;
    }

    public String getObjectType() {
        return list.getObjectType();
    }

    public void register(TopicConsumer consumer) {
        subscriptions.add(consumer);
    }

    public void registerAll() throws IOException {
        register(new MutableObjectProperty(channel, this, "id", TopicNames.OBJECT_ID));
        register(new MutableObjectProperty(channel, this, "name", TopicNames.OBJECT_NAME));
        register(new MutableObjectProperty(channel, this, "props", TopicNames.OBJECT_PROPS));
        register(new DeleteObjectConsumer(channel, this));
    }

    public void unregisterAll() throws IOException {
        for (TopicConsumer consumer : subscriptions) {
            consumer.unsubscribe();
        }
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public String getName() {
        return getProperty("name");
    }

    public int getID() {
        return Integer.parseInt(getProperty("id"));
    }

    public void setProperty(String key, String value) throws IOException {
        if (key == "name") {
            if (list.exists(value)) {
                log.error("Target name already exists, so it cannot be changed.");
                return;
            }
            String old = getName();
            properties.put(key, value);
            list.rename(old, value);
        } else {
            properties.put(key, value);
        }
    }

    public CommandableObject(Channel channel, CommandableObjectList list, String name, CommandQueue cmd) {
        properties = new HashMap<String, String>();
        subscriptions = new ArrayList<TopicConsumer>();
        this.channel = channel;
        this.list = list;
        this.cmd = cmd;
        properties.put("name", name);
    }
}
