package com.github.hanavan99.controller.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.fazecast.jSerialComm.SerialPort;

public class ControlServer {
	public static void main(String[] args) {
		System.out.println("Usage: java -jar [name of jar] [serial port name] [network server port]");
		try {
			final SerialPort port = SerialPort.getCommPort(args[0]);
			port.setBaudRate(9600);
			port.setNumDataBits(8);
			port.setNumStopBits(1);
			port.setParity(SerialPort.NO_PARITY);
			port.openPort();
			System.out.println("Opened comm port");
			ServerSocket server = new ServerSocket(Integer.valueOf(args[1]));
			System.out.println("Server started");
			while (true) {
				final Socket client = server.accept();
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("Client connected");
						try {
							DataInputStream in = new DataInputStream(client.getInputStream());
							while (true) {
								int code = in.readInt();
								System.out.println("Read code " + Long.toBinaryString(code));
								byte[] data = new byte[3];
								data[0] = (byte) (code >> 16);
								data[1] = (byte) (code >> 8);
								data[2] = (byte) (code & 0xFF);
								port.writeBytes(data, data.length);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.println("Connection to client terminated");
					}
				});
				t.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}