package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class DeleteObjectConsumer extends TopicConsumer {
    private final CommandableObject obj;

    @Override
    public void handleDeliverySafe(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        obj.list.delete(obj);
    }

    public DeleteObjectConsumer(Channel channel, CommandableObject obj) throws IOException {
        super(channel, String.format(TopicNames.OBJECT_DELETE, obj.getObjectType(), obj.getProperty("name")));
        this.obj = obj;
    }
}
