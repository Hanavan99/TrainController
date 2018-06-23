package controller.net;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

import controller.core.Accessory;
import controller.core.Engine;
import controller.core.Route;
import controller.core.SerialCommandBase;
import controller.core.Switch;
import controller.core.enums.CommandSet;
import controller.core.enums.RequestType;
import controller.io.ResourceManager;
import controller.net.packets.CommandPacket;
import controller.net.packets.RequestPacket;
import controller.net.packets.ResponsePacket;

public class PacketServer {

	// http://www.lionel.com/lcs/resources/LCS-LEGACY-Protocol-Spec-v1.21.pdf

	private String address;
	private int port;
	private String comPort;
	private ServerSocket server;
	private Thread serverThread;
	private Thread repeatThread;
	private ArrayList<CommandPacket> repeats = new ArrayList<CommandPacket>();
	private SerialCommandBase base;
	private ResourceManager manager;

	public PacketServer() {
		manager = new ResourceManager(new File("."));
	}

	private void createThreads() {
		serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Server thread started");
				try {
					server = new ServerSocket();
					if (address == null) {
						server.bind(new InetSocketAddress(port));
					} else {
						server.bind(new InetSocketAddress(address, port));
					}
					while (true) {
						try {
							Thread.sleep(1);
							final Socket client = server.accept();
							System.out.println("Client connected");
							Thread clientThread = new Thread(new Runnable() {
								@Override
								public void run() {
									try {
										ObjectInputStream in = new ObjectInputStream(client.getInputStream());
										ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
										while (true) {
											try {
												Object packet = in.readObject();
												if (packet instanceof CommandPacket) {
													System.out.println("Recieved command");
													CommandPacket command = (CommandPacket) packet;
													switch (command.getType()) {
													case NORMAL:
														base.sendCommand(command.toIntCommand());
														break;
													case REPEAT_START:
														repeats.add(command);
														break;
													case REPEAT_STOP:
														repeats.remove(command);
														break;
													default:
														System.out.println("How!?");
														break;
													}
												} else if (packet instanceof RequestPacket) {
													System.out.println("Recieved request");
													RequestPacket request = (RequestPacket) packet;
													switch (request.getRequestType()) {
													case GET_ACCESSORIES:
														out.writeObject(new ResponsePacket(RequestType.GET_ACCESSORIES, manager.getAccessories()));
														System.out.println("Sent " + manager.getAccessories().length + " accessory entries to client");
														break;
													case GET_ENGINES:
														out.writeObject(new ResponsePacket(RequestType.GET_ENGINES, manager.getEngines()));
														System.out.println("Sent " + manager.getEngines().length + " engine entries to client");
														break;
													case GET_ROUTES:
														out.writeObject(new ResponsePacket(RequestType.GET_ROUTES, manager.getRoutes()));
														System.out.println("Sent " + manager.getRoutes().length + " route entries to client");
														break;
													case GET_SWITCHES:
														out.writeObject(new ResponsePacket(RequestType.GET_SWITCHES, manager.getSwitches()));
														System.out.println("Sent " + manager.getSwitches().length + " switch entries to client");
														break;
													case SET_ACCESSORY:
														manager.updateAccessory((Accessory) request.getArgs()[0]);
														break;
													case SET_ENGINE:
														manager.updateEngine((Engine) request.getArgs()[0]);
														break;
													case SET_ROUTE:
														manager.updateRoute((Route) request.getArgs()[0]);
														break;
													case SET_SWITCH:
														manager.updateSwitch((Switch) request.getArgs()[0]);
														break;
													case GET_USER_PERMISSIONS:
														// TODO implement
														break;
													default:
														break;

													}
												}
											} catch (IOException e) {
												System.out.println("Client was disconnected");
												break;
											} catch (ClassNotFoundException e) {
												System.out.println("Serialized object does not exist on the server");
											}
										}
									} catch (IOException e) {
										e.printStackTrace();
									}
								}
							});
							clientThread.start();
						} catch (InterruptedException e) {
							break;
						}
					}
				} catch (IOException e) {
					System.out.println("Server socket was closed");
					e.printStackTrace();
				}
				System.out.println("Server thread died");
			}
		});
		repeatThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Repeat thread started");
				try {
					while (true) {
						Thread.sleep(100);
						for (CommandPacket packet : repeats) {
							base.sendCommand(packet.toIntCommand());
						}
					}
				} catch (InterruptedException e) {

				}
				System.out.println("Repeat thread died");
			}
		});
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setComPort(String comPort) {
		this.comPort = comPort;
	}

	public void start() {
		manager.loadResources();
		base = new SerialCommandBase(SerialPort.getCommPort(comPort), CommandSet.TMCC);
		base.start();
		createThreads();
		serverThread.start();
		repeatThread.start();
	}

	public void stop() {
		repeatThread.interrupt();
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverThread.interrupt();
		base.stop();
		manager.saveResources();
		repeats.clear();
	}

}
