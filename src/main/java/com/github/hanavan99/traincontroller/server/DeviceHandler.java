package com.github.hanavan99.traincontroller.server;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeviceHandler extends TopicConsumer {
    private static final Logger log = LogManager.getLogger();
    private final SerialPort port;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        if (!this.port.isOpen()) {
            this.port.setBaudRate(9600);
            this.port.setNumDataBits(8);
            this.port.setNumStopBits(1);
            this.port.setParity(SerialPort.NO_PARITY);
            this.port.openPort();
        }
        this.port.writeBytes(body, body.length);
    }

    @Override
    protected void finalize() throws Throwable {
        this.port.closePort();
    }

    public static void createAll(Channel channel) throws IOException {
        for (SerialPort port : SerialPort.getCommPorts()) {
            new DeviceHandler(channel, port);
        }
    }

    public DeviceHandler(Channel channel, SerialPort port) throws IOException {
        super(channel, String.format(TopicNames.DEVICE_TOPIC, port.getSystemPortName()));
        this.port = port;
        log.info("Found serial port %s.", port.getSystemPortName());
    }
}
