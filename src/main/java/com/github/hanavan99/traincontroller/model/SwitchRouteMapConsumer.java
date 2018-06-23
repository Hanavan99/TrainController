package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.CommandType;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwitchRouteMapConsumer extends TopicConsumer {
    private static final Logger log = LogManager.getLogger();
    private final Switch s;
    private final RouteList routes;
    private final CommandType command;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String routeName = new String(body, "UTF-8");
        for (CommandableObject route : routes.getValues()) {
            if (route.getName() == routeName) {
                routes.getCommandBase().runOnce(command, s.getID(), route.getID());
                return;
            }
        }
        log.warn("Route with name '{}' not found.", routeName);
    }

    public SwitchRouteMapConsumer(Channel channel, Switch s, String topic, RouteList routes, CommandType command) throws IOException {
        super(channel, String.format(topic, s.getName()));
        this.s = s;
        this.routes = routes;
        this.command = command;
    }
}
