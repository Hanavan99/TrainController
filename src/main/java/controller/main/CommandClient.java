package controller.main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import controller.core.Commands;
import controller.core.Engine;
import controller.core.Resource;
import controller.core.Switch;
import controller.core.enums.CommandSet;
import controller.core.enums.CommandType;
import controller.core.enums.RequestType;
import controller.net.packets.CommandPacket;
import controller.net.packets.RequestPacket;
import controller.net.packets.ResponsePacket;

public class CommandClient {

	public static void main(String[] args) {

		try {
			System.out.println("Connecting to server...");
			Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			System.out.println("Connected. Enter commands or \"/exit\" to quit.");
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
							System.out.println(e);
						}
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			s.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
