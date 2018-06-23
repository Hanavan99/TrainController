package com.github.hanavan99.traincontroller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

import com.rabbitmq.client.Channel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandQueue extends Thread {
    private static final int PERIOD = 100;
    private static final Logger log = LogManager.getLogger();
    private final Channel channel;
    private final Semaphore commandLock;
    private final Set<Integer> commands;
    private final String topic;

    private void runOnce(int command) throws IOException {
        byte[] body = new byte[3];
        body[0] = (byte) ((command >> 16) & 0xFF);
        body[1] = (byte) ((command >> 8) & 0xFF);
        body[2] = (byte) (command & 0xFF);
        channel.basicPublish(topic, "", null, body);
    }

    @Override
    public void run() {
        try {
            while (true) {
                commandLock.acquire();
                Integer[] commands = this.commands.toArray(new Integer[0]);
                commandLock.release();
                for (int i : commands) {
                    runOnce(i);
                }
                Thread.sleep(PERIOD);
            }
        } catch (InterruptedException ex) {
        } catch (IOException ex) {
            log.catching(ex);
        }
    }

    public void runOnce(CommandType command, int address, int data) throws IOException {
        runOnce(command.formatCommand(address, data));
    }

    public void runOnce(CommandType command, int address) throws IOException {
        runOnce(command, address, -1);
    }

    public void start(CommandType command, int address, int data) throws IOException {
        try {
            commandLock.acquire();
            Integer cmd = command.formatCommand(address, data);
            if (!commands.add(cmd)) {
                log.warn("Command double-scheduled");
            }
            commandLock.release();
        } catch (InterruptedException ex) {
            throw new IOException(ex);
        }
    }

    public void start(CommandType command, int address) throws IOException {
        start(command, address, -1);
    }

    public void stop(CommandType command, int address, int data) throws IOException {
        try {
            commandLock.acquire();
            Integer cmd = command.formatCommand(address, data);
            if (!commands.remove(cmd)) {
                log.warn("Command removed that was not found");
            }
            commandLock.release();
        } catch (InterruptedException ex) {
            throw new IOException(ex);
        }
    }

    public void stop(CommandType command, int address) throws IOException {
        stop(command, address, -1);
    }

    public CommandQueue(Channel channel, String device) {
        super("Command-Queue");
        this.channel = channel;
        commandLock = new Semaphore(1);
        commands = new HashSet<Integer>();
        this.topic = String.format(TopicNames.DEVICE_TOPIC, device);
    }
}
