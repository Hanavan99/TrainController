package com.github.hanavan99.traincontroller.server;

import com.rabbitmq.client.AMQP.BasicProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class DeviceListProviderTest {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String callbackQueueName = channel.queueDeclare().getQueue();
        BasicProperties props = new BasicProperties.Builder().replyTo(callbackQueueName).build();
        channel.basicPublish(TopicNames.DEVICE_LIST_TOPIC, "", props, new byte[0]);
        channel.basicConsume(callbackQueueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
                log.info("Connected serial ports: {}", new String(body, "UTF-8"));
            }
        });
    }
}
