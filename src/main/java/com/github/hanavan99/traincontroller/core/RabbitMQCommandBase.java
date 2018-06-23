package com.github.hanavan99.traincontroller.core;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.enums.CommandSet;
import com.rabbitmq.client.Channel;

public class RabbitMQCommandBase extends SerialCommandBase {
	public RabbitMQCommandBase(Channel channel, String portName, CommandSet commandSet) {
		this.channel = channel;
		setPortName(portName);
		setCommandSet(commandSet);
	}

	private Channel channel;

	@Override
	public void sendCommand(Object command) {
		try {
			byte[] data = new byte[3];
			data[0] = (byte) getCommandSet().getCode();
			data[1] = (byte) ((int) command >> 8);
			data[2] = (byte) ((int) command & 0xFF);
			channel.basicPublish("/raw/" + getPortName(), "", null, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
		try {
			channel.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
