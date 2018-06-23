package com.github.hanavan99.traincontroller.core;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.enums.CommandSet;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQCommandBase extends SerialCommandBase {

	private String address;

	public RabbitMQCommandBase(String address, String portName, CommandSet commandSet) {
		this.address = address;
		setPortName(portName);
		setCommandSet(commandSet);
	}

	private Connection conn;
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
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(address);
			factory.setVirtualHost("/");
			factory.setUsername("root");
			factory.setPassword("root");
			conn = factory.newConnection();
			channel = conn.createChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			channel.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
