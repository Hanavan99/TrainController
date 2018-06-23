package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public abstract class SwitchRouteMapConsumer extends TopicConsumer {
    private final Switch s;
    private final RouteList routes;

    public abstract void map(com.github.hanavan99.traincontroller.core.Switch s, com.github.hanavan99.traincontroller.core.Route r);

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String routeName = new String(body, "UTF-8");
        com.github.hanavan99.traincontroller.core.Route r = new com.github.hanavan99.traincontroller.core.Route();
        for (CommandableObject route : routes.getValues()) {
            if (route.getName() == routeName) {
                r.setID(route.getID());
                break;
            }
        }
        map(new com.github.hanavan99.traincontroller.core.Switch(s.getID(), null), r);
    }

    public SwitchRouteMapConsumer(Channel channel, Switch s, String topic, RouteList routes) throws IOException {
        super(channel, String.format(topic, s.getName()));
        this.s = s;
        this.routes = routes;
    }
}
