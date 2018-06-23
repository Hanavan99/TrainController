package com.github.hanavan99.traincontroller.model;

import java.io.IOException;
import java.util.Map;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.rabbitmq.client.Channel;

public class EngineList extends CommandableObjectList {
	@Override
	public String getObjectType() {
		return "engine";
	}

	@Override
	public CommandableObject create(String name) throws IOException {
		return new Engine(getChannel(), this, name, getCommandBase());
	}

	@Override
	public CommandableObject create(Map<String, String> properties) {
		return new Engine(getChannel(), this, properties, getCommandBase());
	}

    public EngineList(Channel channel, CommandQueue cmd) throws IOException {
        super(channel, cmd);
    }
}
