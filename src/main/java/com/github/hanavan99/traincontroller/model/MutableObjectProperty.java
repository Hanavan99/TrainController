package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class MutableObjectProperty extends TopicConsumer {
    private final CommandableObject obj;
    private final String propertyName;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        if (body.length == 0) {
            // get
            getChannel().basicPublish("", properties.getReplyTo(),
                    new BasicProperties.Builder().correlationId(properties.getCorrelationId()).build(), obj.getProperty(propertyName).getBytes("UTF-8"));
        } else {
            // set
            obj.setProperty(propertyName, new String(body, "UTF-8"));
        }
    }

    public MutableObjectProperty(Channel channel, CommandableObject obj, String propertyName, String topicName) throws IOException {
        super(channel, String.format(topicName, obj.getObjectType(), obj.getName()));
        this.obj = obj;
        this.propertyName = propertyName;
    }
}
