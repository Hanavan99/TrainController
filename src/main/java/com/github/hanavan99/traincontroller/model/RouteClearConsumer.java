package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class RouteClearConsumer extends TopicConsumer {
    private final Route route;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        com.github.hanavan99.traincontroller.core.Route r = new com.github.hanavan99.traincontroller.core.Route();
        r.setID(route.getID());
        route.getCommandBase().routeClear(r);
    }

    public RouteClearConsumer(Channel channel, Route route) throws IOException {
        super(channel, String.format(TopicNames.CLEAR_ROUTE, route.getName()));
        this.route = route;
    }
}
