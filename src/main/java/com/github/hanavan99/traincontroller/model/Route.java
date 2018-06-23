package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.rabbitmq.client.Channel;

public class Route extends CommandableObject {
    @Override
    public void registerAll() throws IOException {
        super.registerAll();
        register(new RouteThrowConsumer(getChannel(), this));
        register(new RouteClearConsumer(getChannel(), this));
    }

    public Route(Channel channel, RouteList list, String name, RabbitMQCommandBase cmd) {
        super(channel, list, name, cmd);
    }
}
