package com.github.hanavan99.traincontroller.net;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;
import com.github.hanavan99.traincontroller.core.Accessory;
import com.github.hanavan99.traincontroller.core.Engine;
import com.github.hanavan99.traincontroller.core.Route;
import com.github.hanavan99.traincontroller.core.SerialCommandBase;
import com.github.hanavan99.traincontroller.core.Switch;
import com.github.hanavan99.traincontroller.core.enums.CommandSet;
import com.github.hanavan99.traincontroller.core.enums.RequestType;
import com.github.hanavan99.traincontroller.io.ResourceManager;
import com.github.hanavan99.traincontroller.net.packets.CommandPacket;
import com.github.hanavan99.traincontroller.net.packets.RequestPacket;
import com.github.hanavan99.traincontroller.net.packets.ResponsePacket;

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

	private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();

	public PacketServer() {
		manager = new ResourceManager(new File("."));
	}

	private void createThreads() {
		serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("Server thread started");
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
							logger.info("Client connected");
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
													logger.info("Recieved command");
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
													logger.info("Recieved request");
													RequestPacket request = (RequestPacket) packet;
													switch (request.getRequestType()) {
													case GET_ACCESSORIES:
														out.writeObject(new ResponsePacket(RequestType.GET_ACCESSORIES, manager.getAccessories()));
														logger.info("Sent " + manager.getAccessories().length + " accessory entries to client");
														break;
													case GET_ENGINES:
														out.writeObject(new ResponsePacket(RequestType.GET_ENGINES, manager.getEngines()));
														logger.info("Sent " + manager.getEngines().length + " engine entries to client");
														break;
													case GET_ROUTES:
														out.writeObject(new ResponsePacket(RequestType.GET_ROUTES, manager.getRoutes()));
														logger.info("Sent " + manager.getRoutes().length + " route entries to client");
														break;
													case GET_SWITCHES:
														out.writeObject(new ResponsePacket(RequestType.GET_SWITCHES, manager.getSwitches()));
														logger.info("Sent " + manager.getSwitches().length + " switch entries to client");
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
												logger.info("Client was disconnected");
												logger.catching(e);
												break;
											} catch (ClassNotFoundException e) {
												logger.info("Serialized object does not exist on the server");
												logger.catching(e);
											}
										}
									} catch (IOException e) {
										logger.catching(e);
									}
								}
							});
							clientThread.start();
						} catch (InterruptedException e) {
							logger.catching(e);
							break;
						}
					}
				} catch (IOException e) {
					logger.info("Server socket was closed");
					logger.catching(e);
				}
				logger.info("Server thread died");
			}
		});
		repeatThread = new Thread(new Runnable() {
			@Override
			public void run() {
				logger.info("Repeat thread started");
				try {
					while (true) {
						Thread.sleep(100);
						for (CommandPacket packet : repeats) {
							base.sendCommand(packet.toIntCommand());
						}
					}
				} catch (InterruptedException e) {

				}
				logger.info("Repeat thread died");
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
			logger.catching(e);
		}
		serverThread.interrupt();
		base.stop();
		manager.saveResources();
		repeats.clear();
	}

}
