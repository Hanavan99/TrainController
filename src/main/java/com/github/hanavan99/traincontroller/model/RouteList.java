package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.Map;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.rabbitmq.client.Channel;

public class RouteList extends CommandableObjectList {
	@Override
	public String getObjectType() {
		return "route";
	}

	@Override
	public CommandableObject create(String name) throws IOException {
		return new Route(getChannel(), this, name, getCommandBase());
    }

	@Override
	public CommandableObject create(Map<String, String> properties) {
		return new Route(getChannel(), this, properties, getCommandBase());
	}
    
    public RouteList(Channel channel, CommandQueue cmd) throws IOException {
        super(channel, cmd);
    }
}
