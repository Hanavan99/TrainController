package com.github.hanavan99.traincontroller.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zeroturnaround.exec.ProcessResult;
import org.zeroturnaround.exec.StartedProcess;

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq;
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig;
import io.arivera.oss.embedded.rabbitmq.bin.RabbitMqCommand;

public class Main {
    private static final Logger log = LogManager.getLogger();

    public static void addUser(EmbeddedRabbitMqConfig config) throws Exception {
        RabbitMqCommand cmd = new RabbitMqCommand(config, "rabbitmqctl", "add_user", "root", "root");
        StartedProcess proc = cmd.call();
        ProcessResult res = proc.getFuture().get();
        if (res.getExitValue() != 0) {
            log.fatal("Failed to create user");
            log.info(res.outputString());
        }
        cmd = new RabbitMqCommand(config, "rabbitmqctl", "set_user_tags", "root", "administrator");
        proc = cmd.call();
        res = proc.getFuture().get();
        if (res.getExitValue() != 0) {
            log.fatal("Failed to make user an admin");
            log.info(res.outputString());
        }
        cmd = new RabbitMqCommand(config, "rabbitmqctl", "set_permissions", "-p", "/", "root", ".*", ".*", ".*");
        proc = cmd.call();
        res = proc.getFuture().get();
        if (res.getExitValue() != 0) {
            log.fatal("Failed to set user permissions");
            log.info(res.outputString());
        }
    }

    public static void main(String[] args) {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().rabbitMqServerInitializationTimeoutInMillis(10000).build();
        EmbeddedRabbitMq rabbitMq = new EmbeddedRabbitMq(config);
        try {
            rabbitMq.start();
            addUser(config);
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setVirtualHost("/");
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            DeviceHandler.createAll(channel);
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException ex) {
            rabbitMq.stop();
		} catch (Exception ex) {
            log.catching(ex);
            log.error("Unable to start server.");
        }
    }
}
