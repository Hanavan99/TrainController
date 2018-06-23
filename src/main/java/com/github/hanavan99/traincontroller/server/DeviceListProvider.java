package com.github.hanavan99.traincontroller.server;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class DeviceListProvider extends TopicConsumer {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        SerialPort[] ports = SerialPort.getCommPorts();
        String[] portNames = new String[ports.length];
        for (int i = 0; i < ports.length; ++i) {
            portNames[i] = ports[i].getSystemPortName();
        }
        String outStr = String.join(";", portNames);
        BasicProperties props = new BasicProperties.Builder().correlationId(properties.getCorrelationId()).build();
        getChannel().basicPublish("", properties.getReplyTo(), props, outStr.getBytes("UTF-8"));
    }

    public DeviceListProvider(Channel channel) throws IOException {
        super(channel, TopicNames.DEVICE_LIST_TOPIC);
    }
}
