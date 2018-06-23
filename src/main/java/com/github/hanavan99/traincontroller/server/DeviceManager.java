package com.github.hanavan99.traincontroller.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeviceManager extends TopicConsumer {
    private static final Logger log = LogManager.getLogger();
    private final Map<String, DeviceHandler> devices;
    private final Channel channel;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String port = new String(body, "UTF-8");
        if (!devices.containsKey(port)) {
            devices.put(port, new DeviceHandler(channel, port));
            log.info("Opened serial port {}.", port);
        }
    }

    public DeviceManager(Channel channel) throws IOException {
        super(channel, TopicNames.DEVICE_START_TOPIC);
        devices = new HashMap<String, DeviceHandler>();
        this.channel = channel;
    }
}
