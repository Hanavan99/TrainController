package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.Map;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.rabbitmq.client.Channel;

public class SwitchList extends CommandableObjectList {
	private final RouteList routes;

	@Override
	public String getObjectType() {
		return "switch";
	}

	@Override
	public CommandableObject create(String name) throws IOException {
		return new Switch(getChannel(), this, name, getCommandBase(), routes);
	}

	@Override
	public CommandableObject create(Map<String, String> properties) {
		return new Switch(getChannel(), this, properties, getCommandBase(), routes);
	}
    
	public SwitchList(Channel channel, CommandQueue cmd, RouteList routes) throws IOException {
		super(channel, cmd);
		this.routes = routes;
	}
}
