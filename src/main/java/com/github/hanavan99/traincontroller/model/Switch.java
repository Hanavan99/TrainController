package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicNames;
import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.rabbitmq.client.Channel;

public class Switch extends CommandableObject {
    private final RouteList routes;

    @Override
    public void registerAll() throws IOException {
        super.registerAll();
        register(new SwitchThrowConsumer(getChannel(), this));
        register(new SwitchRouteMapConsumer(getChannel(), this, TopicNames.SWITCH_ADD_THROUGH, routes) {
			@Override
			public void map(com.github.hanavan99.traincontroller.core.Switch s, com.github.hanavan99.traincontroller.core.Route r) {
				getCommandBase().switchAssignToRouteThrough(s, r);
			}
        });
        register(new SwitchRouteMapConsumer(getChannel(), this, TopicNames.SWITCH_ADD_OUT, routes) {
            @Override
			public void map(com.github.hanavan99.traincontroller.core.Switch s, com.github.hanavan99.traincontroller.core.Route r) {
				getCommandBase().switchAssignToRouteOut(s, r);
			}
        });
    }

    public Switch(Channel channel, SwitchList list, String name, RabbitMQCommandBase cmd, RouteList routes) throws IOException {
        super(channel, list, name, cmd);
        this.routes = routes;
    }
}
