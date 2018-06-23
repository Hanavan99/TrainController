package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.rabbitmq.client.Channel;

public class DataContext {
    public final RouteList routes;
    public final SwitchList switches;
    public final EngineList engines;

    public DataContext(Channel channel, CommandQueue cmd) throws IOException {
        routes = new RouteList(channel, cmd);
        switches = new SwitchList(channel, cmd, routes);
        engines = new EngineList(channel, cmd);
    }
}
