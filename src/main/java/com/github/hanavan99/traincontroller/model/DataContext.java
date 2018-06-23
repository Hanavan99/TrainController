package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.rabbitmq.client.Channel;

public class DataContext {
    public final RouteList routes;
    public final SwitchList switches;

    public DataContext(Channel channel, RabbitMQCommandBase cmd) throws IOException {
        routes = new RouteList(channel, cmd);
        switches = new SwitchList(channel, cmd, routes);
    }
}
