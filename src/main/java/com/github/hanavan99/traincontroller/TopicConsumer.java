package com.github.hanavan99.traincontroller;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;

public abstract class TopicConsumer extends DefaultConsumer {
    public TopicConsumer(Channel channel, String topic, boolean autoack) throws IOException {
        super(channel);
        channel.exchangeDeclare(topic, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, topic, "");
        channel.basicConsume(queueName, autoack, this);
    }

    public TopicConsumer(Channel channel, String topic) throws IOException {
        this(channel, topic, true);
    }
}
