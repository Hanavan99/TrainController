package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class NewObjectConsumer extends TopicConsumer {
    private final CommandableObjectList list;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String name = new String(body, "UTF-8");
        if (!list.exists(name)) {
            list.add(name);
        }
    }

    public NewObjectConsumer(Channel channel, CommandableObjectList list) throws IOException {
        super(channel, String.format(TopicNames.OBJECT_NEW, list.getObjectType()));
        this.list = list;
    }
}
