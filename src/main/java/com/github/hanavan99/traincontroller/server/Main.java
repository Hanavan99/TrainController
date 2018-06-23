package com.github.hanavan99.traincontroller.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;

public class Main {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().rabbitMqServerInitializationTimeoutInMillis(10000).build();
        EmbeddedRabbitMq rabbitMq = new EmbeddedRabbitMq(config);
        try {
            rabbitMq.start();
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setVirtualHost("/");
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            new DeviceListProvider(channel);
            new DeviceManager(channel);
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException ex) {
            rabbitMq.stop();
		} catch (Exception ex) {
            log.catching(ex);
            log.error("Unable to start server.");
        }
    }
}
