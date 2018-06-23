package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

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
    
    public RouteList(Channel channel, CommandQueue cmd) throws IOException {
        super(channel, cmd);
    }
}
