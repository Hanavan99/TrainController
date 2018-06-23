package com.github.hanavan99.traincontroller.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.github.hanavan99.traincontroller.core.Commands;
import com.github.hanavan99.traincontroller.core.Engine;
import com.github.hanavan99.traincontroller.core.Resource;
import com.github.hanavan99.traincontroller.core.Switch;
import com.github.hanavan99.traincontroller.core.enums.CommandType;
import com.github.hanavan99.traincontroller.core.enums.RequestType;
import com.github.hanavan99.traincontroller.net.packets.CommandPacket;
import com.github.hanavan99.traincontroller.net.packets.RequestPacket;
import com.github.hanavan99.traincontroller.net.packets.ResponsePacket;

public class CommandClient {

	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();

	public static void main(String[] args) {

		try {
			logger.info("Connecting to server...");
			Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			logger.info("Connected. Enter commands or \"/exit\" to quit.");
			Scanner s = new Scanner(System.in);
			String command;
			while (!(command = s.nextLine()).equals("/exit")) {
				try {
					String[] cmd = command.split(" ");
					switch (cmd[0]) {
					case "/switch":
						switch (cmd[2]) {
						case "throw":
							int arg0 = -1;
							if (cmd[3].equals("out")) {
								arg0 = Commands.SWITCH_THROW_OUT;
							} else if (cmd[3].equals("through")) {
								arg0 = Commands.SWITCH_THROW_THROUGH;
							}
							out.writeObject(new CommandPacket(CommandType.NORMAL, new Switch(Integer.parseInt(cmd[1]), null), arg0, null));
							break;
						case "add":
							out.writeObject(new RequestPacket(RequestType.SET_SWITCH, new Resource[] { new Switch(Integer.parseInt(cmd[1]), cmd[3]) }, null));
							break;
						}
						break;
					case "/engine":
						switch (cmd[2]) {
						case "bell":
							out.writeObject(new CommandPacket(CommandType.NORMAL, new Engine(Integer.parseInt(cmd[1]), null), Commands.ENGINE_BELL, null));
							break;
						case "add":
							out.writeObject(new RequestPacket(RequestType.SET_ENGINE, new Resource[] { new Engine(Integer.parseInt(cmd[1]), cmd[3]) }, null));
							break;
						}
						break;
					case "/engines":
						out.writeObject(new RequestPacket(RequestType.GET_ENGINES, null, null));
						for (Engine e : (Engine[]) ((ResponsePacket) in.readObject()).getArgs()) {
							logger.info(e);
						}
						break;
					}
				} catch (Exception e) {
					logger.catching(e);
				}
			}
			s.close();
			socket.close();
		} catch (IOException e) {
			logger.catching(e);
		}

	}

}
