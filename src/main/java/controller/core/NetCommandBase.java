package controller.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import controller.net.packets.CommandPacket;

public class NetCommandBase extends LionelBase {

	private String hostname;
	private int port;
	private Socket socket;
	private ObjectOutputStream out;
	private User user;

	public NetCommandBase(String hostname, int port, User user) {
		this.hostname = hostname;
		this.port = port;
		this.user = user;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ObjectInputStream createObjectInputStream() throws IOException {
		if (socket != null) {
			return new ObjectInputStream(socket.getInputStream());
		} else {
			throw new IllegalStateException("Cannon create input stream from null socket");
		}
	}

	@Override
	public void sendCommand(Object command) {
		if (out != null) {
			try {
				out.writeObject(command);
			} catch (IOException e) {
				System.out.println("Failed to write a packet");
			}
		}
	}

	@Override
	public void switchThrowThrough(Switch sw) {
		sendCommand(new CommandPacket(getCommandType(), sw, Commands.SWITCH_THROW_THROUGH, user));
	}

	@Override
	public void switchThrowOut(Switch sw) {
		sendCommand(new CommandPacket(getCommandType(), sw, Commands.SWITCH_THROW_OUT, user));
	}

	@Override
	public void switchSetAddress(Switch sw) {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchAssignToRouteThrough(Switch sw, Route rte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchAssignToRouteOut(Switch sw, Route rte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void routeThrow(Route rte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void routeClear(Route rte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineDirectionForward(Engine eng) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_DIRECTION_FORWARD, user));
	}

	@Override
	public void engineDirectionToggle(Engine eng) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_DIRECTION_TOGGLE, user));
	}

	@Override
	public void engineDirectionReverse(Engine eng) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_DIRECTION_REVERSE, user));
	}

	@Override
	public void engineBoostSpeed(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineBrakeSpeed(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineOpenFrontCoupler(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineOpenRearCoupler(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineBlowHorn1(Engine eng) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_HORN_1, user));
	}

	@Override
	public void engineRingBell(Engine eng) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_BELL, user));
	}

	@Override
	public void engineLetoffSound(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineBlowHorn2(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux1Off(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux1Option1(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux1Option2(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux1On(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux2Off(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux2Option1(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux2Option2(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAux2On(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineSendNumericOption(Engine eng, int num) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignToTrain(Engine eng, Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignSingleForward(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignSingleReverse(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignHeadForward(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignHeadReverse(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignMiddleForward(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignMiddleReverse(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignRearForward(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineAssignRearReverse(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineMomentumLow(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineMomentumMedium(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineMomentumHigh(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineSetTrainAddress(Engine eng, Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trainMomentumLow(Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trainMomentumMedium(Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trainMomentumHigh(Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trainSetAddress(Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trainClear(Train train) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineSpeedAbsolute(Engine eng, int speed) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_SPEED_ABSOLUTE | speed, user));
	}

	@Override
	public void engineSpeedRelative(Engine eng, int speed) {
		sendCommand(new CommandPacket(getCommandType(), eng, Commands.ENGINE_SPEED_RELATIVE | speed, user));
	}

	@Override
	public void start() {
		try {
			socket = new Socket(hostname, port);
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Failed to create socket");
			socket = null;
			out = null;
		}
	}

	@Override
	public void stop() {
		try {
			socket.close();
			socket = null;
			out = null;
		} catch (IOException e) {

		}
	}

}
