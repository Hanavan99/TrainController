package com.github.hanavan99.traincontroller;

import com.github.hanavan99.traincontroller.model.DataContext;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandInterpreter {
    private static final long SAVE_PERIOD = 5000;
    private static final Logger log = LogManager.getLogger();

    public static void main(String[] args) {
        DataContext data = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("192.168.1.247");
            factory.setVirtualHost("/");
            factory.setUsername("root");
            factory.setPassword("root");
            Connection conn = factory.newConnection();
            Channel channel = conn.createChannel();
            CommandQueue cmd = new CommandQueue(channel, "ttyS0");
            data = new DataContext(channel, cmd);
            data.load();
            cmd.start();
            log.info("Command interpreter is now running.");
            while (true) {
                Thread.sleep(SAVE_PERIOD);
                data.save();
            }
        } catch (Exception ex) {
            log.catching(ex);
            if (data != null) {
                data.save();
            }
        }
    }
}
