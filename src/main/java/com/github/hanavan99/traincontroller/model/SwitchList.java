package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.rabbitmq.client.Channel;

public class SwitchList extends CommandableObjectList {
	@Override
	public String getObjectType() {
		return "switch";
	}

	@Override
	public CommandableObject create(String name) throws IOException {
		return new Switch(getChannel(), this, name, getCommandBase());
    }
    
    public SwitchList(Channel channel, RabbitMQCommandBase cmd) throws IOException {
        super(channel, cmd);
    }
}
