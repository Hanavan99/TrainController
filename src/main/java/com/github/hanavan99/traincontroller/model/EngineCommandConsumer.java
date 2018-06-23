package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.github.hanavan99.traincontroller.CommandType;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public abstract class EngineCommandConsumer extends TopicConsumer {
    private final Engine engine;
    private final CommandType command;

    public abstract void handle(CommandQueue queue, CommandType command, int address, int data) throws IOException;

    @Override
    public void handleDeliverySafe(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body, "UTF-8");
        int data = -1;
        try {
            data = Integer.parseInt(msg);
        } catch (NumberFormatException ex) {
            data = -1;
        }
        handle(engine.getCommandBase(), command, engine.getID(), data);
    }

    public EngineCommandConsumer(Channel channel, Engine engine, String topic, CommandType command) throws IOException {
        super(channel, String.format(topic, engine.getName(), command.getFriendlyName()));
        this.engine = engine;
        this.command = command;
    }
}
