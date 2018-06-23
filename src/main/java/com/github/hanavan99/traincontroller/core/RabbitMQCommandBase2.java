package com.github.hanavan99.traincontroller.core;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQCommandBase2 extends LionelBase {

	private Connection conn;
	private Channel channel;
	private String address;

	public RabbitMQCommandBase2(String address) {
		this.address = address;
	}

	@Override
	public void sendCommand(Object command) {
		try {
			Object[] data = (Object[]) command;
			channel.basicPublish(data[0].toString(), "", null, (byte[]) data[1]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(address);
			factory.setVirtualHost("/");
			factory.setUsername("root");
			factory.setPassword("root");
			conn = factory.newConnection();
			channel = conn.createChannel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			channel.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createSwitch(Switch sw) {
		sendCommand(new Object[] { "/switch/.new", sw.getName().getBytes() });
	}

	public void setSwitchID(Switch sw) {
		sendCommand(new Object[] { "/switch/" + sw.getName() + "/id", String.valueOf(sw.getID()).getBytes() });
	}

	@Override
	public void switchThrowThrough(Switch sw) {
		sendCommand(new Object[] { "/switch/" + sw.getName() + "/throw", "through".getBytes() });
	}

	@Override
	public void switchThrowOut(Switch sw) {
		sendCommand(new Object[] { "/switch/" + sw.getName() + "/throw", "out".getBytes() });
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
		// TODO Auto-generated method stub

	}

	@Override
	public void engineDirectionToggle(Engine eng) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineDirectionReverse(Engine eng) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void engineRingBell(Engine eng) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void engineSpeedRelative(Engine eng, int speed) {
		// TODO Auto-generated method stub

	}

}
