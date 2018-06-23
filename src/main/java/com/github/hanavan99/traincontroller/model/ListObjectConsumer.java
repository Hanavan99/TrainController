package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class ListObjectConsumer extends TopicConsumer {
    private final CommandableObjectList list;

    @Override
    public void handleDeliverySafe(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String res = String.join("\n", list.getNames());
        getChannel().basicPublish("", properties.getReplyTo(),
                new BasicProperties.Builder().correlationId(properties.getCorrelationId()).build(), res.getBytes("UTF-8"));
    }

    public ListObjectConsumer(Channel channel, CommandableObjectList list) throws IOException {
        super(channel, String.format(TopicNames.OBJECT_LIST, list.getObjectType()));
        this.list = list;
    }
}
