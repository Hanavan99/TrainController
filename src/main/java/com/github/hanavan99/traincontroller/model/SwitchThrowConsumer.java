package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SwitchThrowConsumer extends TopicConsumer {
    private static final Logger log = LogManager.getLogger();
    private final Switch s;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String mode = new String(body, "UTF-8");
        switch (mode) {
            case "through":
                s.getCommandBase().switchThrowThrough(new com.github.hanavan99.traincontroller.core.Switch(s.getID(), null));
                break;
            case "out":
                s.getCommandBase().switchThrowOut(new com.github.hanavan99.traincontroller.core.Switch(s.getID(), null));
                break;
            default:
                log.warn("Unknown throw type '{}'", mode);
                break;
        }
    }

    public SwitchThrowConsumer(Channel channel, Switch s) throws IOException {
        super(channel, String.format(TopicNames.THROW_SWITCH, s.getName()));
        this.s = s;
    }
}
