package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.rabbitmq.client.Channel;

public class Switch extends CommandableObject {
    @Override
    public void registerAll() throws IOException {
        super.registerAll();
        register(new SwitchThrowConsumer(getChannel(), this));
    }

    public Switch(Channel channel, SwitchList list, String name, RabbitMQCommandBase cmd) throws IOException {
        super(channel, list, name, cmd);
    }
}
