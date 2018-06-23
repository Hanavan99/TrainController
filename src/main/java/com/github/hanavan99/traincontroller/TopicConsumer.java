package com.github.hanavan99.traincontroller;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class TopicConsumer extends DefaultConsumer {
    private static final Logger log = LogManager.getLogger();
    private final String consumerTag;

    public void unsubscribe() throws IOException {
        getChannel().basicCancel(consumerTag);
    }

    public void handleDeliverySafe(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        try {
            handleDeliverySafe(consumerTag, envelope, properties, body);
        } catch (Exception ex) {
            log.catching(ex);
        }
    }

    public TopicConsumer(Channel channel, String topic, boolean autoack) throws IOException {
        super(channel);
        channel.exchangeDeclare(topic, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, topic, "");
        consumerTag = channel.basicConsume(queueName, autoack, this);
    }

    public TopicConsumer(Channel channel, String topic) throws IOException {
        this(channel, topic, true);
    }
}
