package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.Map;

import com.github.hanavan99.traincontroller.TopicNames;
import com.github.hanavan99.traincontroller.CommandQueue;
import com.github.hanavan99.traincontroller.CommandType;
import com.rabbitmq.client.Channel;

public class Switch extends CommandableObject {
    private final RouteList routes;

    @Override
    public void registerAll() throws IOException {
        super.registerAll();
        register(new SwitchThrowConsumer(getChannel(), this));
        register(new SwitchRouteMapConsumer(getChannel(), this, TopicNames.SWITCH_ADD_THROUGH, routes, CommandType.SwitchAssignRouteThrough));
        register(new SwitchRouteMapConsumer(getChannel(), this, TopicNames.SWITCH_ADD_OUT, routes, CommandType.SwitchAssignRouteOut));
    }

    public Switch(Channel channel, SwitchList list, String name, CommandQueue cmd, RouteList routes) throws IOException {
        super(channel, list, name, cmd);
        this.routes = routes;
    }

    public Switch(Channel channel, SwitchList list, Map<String, String> properties, CommandQueue cmd, RouteList routes) {
        super(channel, list, properties, cmd);
        this.routes = routes;
    }
}
