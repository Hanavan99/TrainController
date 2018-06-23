package com.github.hanavan99.traincontroller;

import java.io.IOException;

import com.github.hanavan99.traincontroller.core.RabbitMQCommandBase;
import com.github.hanavan99.traincontroller.core.enums.CommandSet;
import com.github.hanavan99.traincontroller.model.DataContext;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandInterpreter {
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.1.247");
            factory.setVirtualHost("/");
            factory.setUsername("root");
            factory.setPassword("root");
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            CommandQueue cmd = new CommandQueue(channel, "ttyS0");
            new DataContext(channel, cmd);
            log.info("Command interpreter is now running.");
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception ex) {
            log.catching(ex);
        }
    }
}
