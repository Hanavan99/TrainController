package com.github.hanavan99.traincontroller.client;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.github.hanavan99.traincontroller.core.Switch;
import com.github.hanavan99.traincontroller.core.enums.CommandSet;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQTestClient {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.1.247");
		factory.setVirtualHost("/");
		factory.setUsername("root");
		factory.setPassword("root");
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		RabbitMQCommandBase base = new RabbitMQCommandBase(channel, "ttyS0", CommandSet.TMCC);
		base.start();
		base.switchThrowOut(new Switch(1, null));
		sleep(1000);
		base.switchThrowThrough(new Switch(1, null));
		base.stop();
	}

	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (Exception e) {

		}
	}

}
