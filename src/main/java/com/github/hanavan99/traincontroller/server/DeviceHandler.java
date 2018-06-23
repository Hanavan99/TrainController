package com.github.hanavan99.traincontroller.server;

import java.io.IOException;

import com.fazecast.jSerialComm.SerialPort;
import com.github.hanavan99.traincontroller.TopicConsumer;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class DeviceHandler extends TopicConsumer {
    private final SerialPort port;

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        this.port.writeBytes(body, body.length);
    }

    @Override
    protected void finalize() throws Throwable {
        this.port.closePort();
    }

    public DeviceHandler(Channel channel, String port) throws IOException {
        super(channel, String.format(TopicNames.DEVICE_TOPIC, port));
        this.port = SerialPort.getCommPort(port);
        this.port.setBaudRate(9600);
		this.port.setNumDataBits(8);
		this.port.setNumStopBits(1);
        this.port.setParity(SerialPort.NO_PARITY);
        this.port.openPort();
    }
}
