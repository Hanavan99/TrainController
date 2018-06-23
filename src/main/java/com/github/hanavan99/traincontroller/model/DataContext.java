package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rabbitmq.client.Channel;

public class DataContext {
    public final RouteList routes;
    public final SwitchList switches;
    public final EngineList engines;
    public final CommandableObjectList[] entities;

    private Preferences getPreferences() {
        return Preferences.userNodeForPackage(getClass());
    }

    public void load() throws IOException {
        Gson gson = new Gson();
        Preferences prefs = getPreferences();
        for (CommandableObjectList entity : entities) {
            String json = prefs.get(entity.getObjectType(), "[]");
            List<Map<String, String>> data = gson.fromJson(json, new TypeToken<List<Map<String, String>>>(){}.getType());
            entity.load(data);
        }
    }

    public void save() {
        Gson gson = new Gson();
        Preferences prefs = getPreferences();
        for (CommandableObjectList entity : entities) {
            List<Map<String, String>> data = entity.save();
            String json = gson.toJson(data);
            prefs.put(entity.getObjectType(), json);
        }
    }

    public DataContext(Channel channel, CommandQueue cmd) throws IOException {
        routes = new RouteList(channel, cmd);
        switches = new SwitchList(channel, cmd, routes);
        engines = new EngineList(channel, cmd);
        entities = new CommandableObjectList[] {
            routes,
            switches,
            engines
        };
    }
}
