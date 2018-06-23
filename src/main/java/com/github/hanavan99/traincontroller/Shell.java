package com.github.hanavan99.traincontroller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Shell extends DefaultConsumer {
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
        String msg = new String(body, "UTF-8");
        System.out.println(msg);
    }

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.247");
        factory.setVirtualHost("/");
        factory.setUsername("root");
        factory.setPassword("root");
        Connection conn = factory.newConnection();
        Channel channel = conn.createChannel();
        String queue = channel.queueDeclare().getQueue();
        channel.basicConsume(queue, true, new Shell(channel));
        BasicProperties props = new BasicProperties.Builder().replyTo(queue).build();
        ServerSocket sock = new ServerSocket(10023);
        Socket client = sock.accept();
        Scanner scan = new Scanner(client.getInputStream());
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(" ", 2);
            byte[] body = parts.length == 2 ? parts[1].getBytes("UTF-8") : new byte[0];
            channel.basicPublish(parts[0], "", props, body);
        }
        scan.close();
        client.close();
        sock.close();
    }

    Shell(Channel channel) {
        super(channel);
    }
}
