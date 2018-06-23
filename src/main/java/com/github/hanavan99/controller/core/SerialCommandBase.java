package com.github.hanavan99.controller.core;

import com.fazecast.jSerialComm.SerialPort;
import com.github.hanavan99.controller.core.enums.CommandSet;

public class SerialCommandBase extends LionelBase {

	private CommandSet commandSet;
	private SerialPort port;

	public SerialCommandBase(SerialPort port, CommandSet commandSet) {
		port.setBaudRate(9600);
		port.setNumDataBits(8);
		port.setNumStopBits(1);
		port.setParity(SerialPort.NO_PARITY);
		this.port = port;
		this.commandSet = commandSet;
	}

	@Override
	public void switchSetAddress(Switch sw) {
		sendCommand(Commands.MASK_SWITCH | (sw.getID() << 7) | Commands.SWITCH_SET_ADDRESS);
	}

	@Override
	public void engineDirectionToggle(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_DIRECTION_TOGGLE);
	}

	@Override
	public void engineBlowHorn1(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_HORN_1);
	}

	@Override
	public void engineSendNumericOption(Engine eng, int num) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_NUMERIC_N | num);
	}

	@Override
	public void engineAux1Option1(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX1_OPTION1);
	}

	public void start() {
		port.openPort();
	}

	public void stop() {
		port.closePort();
	}

	@Override
	public void sendCommand(Object command) {
		byte[] data = new byte[3];
		data[0] = (byte) commandSet.getCode();
		data[1] = (byte) ((int) command >> 8);
		data[2] = (byte) ((int) command & 0xFF);
		port.writeBytes(data, data.length);
	}

	@Override
	public void switchThrowThrough(Switch sw) {
		sendCommand(Commands.MASK_SWITCH | (sw.getID() << 7) | Commands.SWITCH_THROW_THROUGH);
	}

	@Override
	public void switchThrowOut(Switch sw) {
		sendCommand(Commands.MASK_SWITCH | (sw.getID() << 7) | Commands.SWITCH_THROW_OUT);
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
	public void engineDirectionForward(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_DIRECTION_FORWARD);
	}

	@Override
	public void engineDirectionReverse(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_DIRECTION_REVERSE);
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
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_COUPLER_FRONT);
	}

	@Override
	public void engineOpenRearCoupler(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_COUPLER_REAR);
	}

	@Override
	public void engineRingBell(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_BELL);
	}

	@Override
	public void engineLetoffSound(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_LETOFF);
	}

	@Override
	public void engineBlowHorn2(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_HORN_2);
	}

	@Override
	public void engineAux1Off(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX1_OFF);
	}

	@Override
	public void engineAux1Option2(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX1_OPTION2);
	}

	@Override
	public void engineAux1On(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX1_ON);
	}

	@Override
	public void engineAux2Off(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX2_OFF);
	}

	@Override
	public void engineAux2Option1(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX2_OPTION1);
	}

	@Override
	public void engineAux2Option2(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX2_OPTION2);
	}

	@Override
	public void engineAux2On(Engine eng) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_AUX2_ON);
	}

	@Override
	public void engineAssignToTrain(Engine eng, Train train) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_ASSIGN_TRAIN | train.getID());
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
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_SPEED_ABSOLUTE | speed);
	}

	@Override
	public void engineSpeedRelative(Engine eng, int speed) {
		sendCommand(Commands.MASK_ENGINE | (eng.getID() << 7) | Commands.ENGINE_SPEED_RELATIVE | speed);
	}

	@Override
	public void routeThrow(Route rte) {
		// TODO Auto-generated method stub

	}

	@Override
	public void routeClear(Route rte) {
		// TODO Auto-generated method stub

	}

}
