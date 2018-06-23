package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.CommandType;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RouteThrowConsumer extends TopicConsumer {
    private final Route route;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        route.getCommandBase().runOnce(CommandType.RouteThrow, route.getID());
    }

    public RouteThrowConsumer(Channel channel, Route route) throws IOException {
        super(channel, String.format(TopicNames.THROW_ROUTE, route.getName()));
        this.route = route;
    }
}
