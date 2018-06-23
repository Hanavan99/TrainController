package com.github.hanavan99.traincontroller.core;

import com.github.hanavan99.traincontroller.core.enums.CommandType;

public abstract class LionelBase {

	private CommandType commandType = CommandType.NORMAL;

	/**
	 * Gets the type of command to be issued.
	 * 
	 * @return the command type
	 */
	public CommandType getCommandType() {
		return commandType;
	}

	/**
	 * Sets the type of command to be issued.
	 * 
	 * @param commandType
	 *            the type of command
	 */
	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	/**
	 * Sends a command.
	 * 
	 * @param command
	 *            the command to send
	 */
	public abstract void sendCommand(Object command);

	/**
	 * Throws a switch to the through/straight direction.
	 * 
	 * @param sw
	 *            the switch to throw
	 */
	public abstract void switchThrowThrough(Switch sw);

	/**
	 * Throws a switch to the out/diverting direction.
	 * 
	 * @param sw
	 *            the switch to throw
	 */
	public abstract void switchThrowOut(Switch sw);

	/**
	 * Sets the address of all switches in the "program" mode.
	 * 
	 * @param sw
	 *            the switch object with the new ID to set
	 */
	public abstract void switchSetAddress(Switch sw);

	/**
	 * Assigns a switch to a specific route in the through/straight direction.
	 * 
	 * @param sw
	 *            the switch to assign
	 * @param rte
	 *            the route to assign to
	 */
	public abstract void switchAssignToRouteThrough(Switch sw, Route rte);

	/**
	 * Assigns a switch to a specific route in the out/diverting direction.
	 * 
	 * @param sw
	 *            the switch to assign
	 * @param rte
	 *            the route to assign to
	 */
	public abstract void switchAssignToRouteOut(Switch sw, Route rte);

	/**
	 * Throws all switches in a route to their specified positions.
	 * 
	 * @param rte
	 *            the route to throw
	 */
	public abstract void routeThrow(Route rte);

	/**
	 * Clears a route.
	 * 
	 * @param rte
	 *            the route to clear
	 */
	public abstract void routeClear(Route rte);

	/**
	 * Sets the direction of an engine to forward. Note that the engine will not
	 * stop if in motion. A set speed command must be issued to change movement
	 * direction.
	 * 
	 * @param eng
	 *            the engine to set the direction of
	 */
	public abstract void engineDirectionForward(Engine eng);

	/**
	 * Toggles an engine's direction. If the engine is in reverse, it will be thrown
	 * to forward, and vice versa. Note that the engine will not stop if in motion.
	 * A set speed command must be issued to change movement direction.
	 * 
	 * @param eng
	 *            the engine to toggle direction
	 */
	public abstract void engineDirectionToggle(Engine eng);

	/**
	 * Sets the direction of an engine to reverse. Note that the engine will not
	 * stop if in motion. A set speed command must be issued to change movement
	 * direction.
	 * 
	 * @param eng
	 *            the engine to set the direction of
	 */
	public abstract void engineDirectionReverse(Engine eng);

	/**
	 * Applies a steady increase in speed as long as the command is active. The
	 * speed will then return back to its starting speed. The rate of speed increase
	 * is based on the engine's momentum setting.
	 * 
	 * @param eng
	 *            the engine to boost
	 */
	public abstract void engineBoostSpeed(Engine eng);

	/**
	 * Applies a steady decrease in speed as long as the command is active. The
	 * speed will then return back to its starting speed. The rate of speed decrease
	 * is based on the engine's momentum setting.
	 * 
	 * @param eng
	 *            the engine to brake
	 */
	public abstract void engineBrakeSpeed(Engine eng);

	/**
	 * Opens the front coupler on an engine.
	 * 
	 * @param eng
	 *            the engine to open the coupler on
	 */
	public abstract void engineOpenFrontCoupler(Engine eng);

	/**
	 * Opens the rear coupler on an engine.
	 * 
	 * @param eng
	 *            the engine to open the coupler on
	 */
	public abstract void engineOpenRearCoupler(Engine eng);

	/**
	 * Blows the horn/whistle on an engine. Some engines have two horns, but if it
	 * only has one, this method should be used to activate it. To blow the horn
	 * continuously, continue issuing this command every 100ms until the horn should
	 * stop sounding.
	 * 
	 * @param eng
	 *            the engine to blow the horn on
	 */
	public abstract void engineBlowHorn1(Engine eng);

	/**
	 * Toggles the bell on an engine.
	 * 
	 * @param eng
	 *            the engine to toggle the bell on
	 */
	public abstract void engineRingBell(Engine eng);

	/**
	 * Activates the let-off sound.
	 * 
	 * @param eng
	 *            the engine to activate the sound on
	 */
	public abstract void engineLetoffSound(Engine eng);

	/**
	 * Blows the second horn/whistle, if equipped.
	 * 
	 * @param eng
	 *            the engine to blow the horn on
	 */
	public abstract void engineBlowHorn2(Engine eng);

	public abstract void engineAux1Off(Engine eng);

	public abstract void engineAux1Option1(Engine eng);

	public abstract void engineAux1Option2(Engine eng);

	public abstract void engineAux1On(Engine eng);

	public abstract void engineAux2Off(Engine eng);

	public abstract void engineAux2Option1(Engine eng);

	public abstract void engineAux2Option2(Engine eng);

	public abstract void engineAux2On(Engine eng);

	/**
	 * Sends an AUX1 + num command to an engine.
	 * 
	 * @param eng
	 *            the engine to send the command to
	 * @param num
	 *            the number (0 - 9) to send
	 */
	public abstract void engineSendNumericOption(Engine eng, int num);

	public abstract void engineAssignToTrain(Engine eng, Train train);

	public abstract void engineAssignSingleForward(Engine eng);

	public abstract void engineAssignSingleReverse(Engine eng);

	public abstract void engineAssignHeadForward(Engine eng);

	public abstract void engineAssignHeadReverse(Engine eng);

	public abstract void engineAssignMiddleForward(Engine eng);

	public abstract void engineAssignMiddleReverse(Engine eng);

	public abstract void engineAssignRearForward(Engine eng);

	public abstract void engineAssignRearReverse(Engine eng);

	public abstract void engineMomentumLow(Engine eng);

	public abstract void engineMomentumMedium(Engine eng);

	public abstract void engineMomentumHigh(Engine eng);

	public abstract void engineSetTrainAddress(Engine eng, Train train);

	public abstract void trainMomentumLow(Train train);

	public abstract void trainMomentumMedium(Train train);

	public abstract void trainMomentumHigh(Train train);

	public abstract void trainSetAddress(Train train);

	public abstract void trainClear(Train train);

	/**
	 * Sets the absolute speed of an engine.
	 * 
	 * @param eng
	 *            the engine to set the speed of
	 * @param speed
	 *            the speed
	 */
	public abstract void engineSpeedAbsolute(Engine eng, int speed);

	/**
	 * Sets the relative speed of an engine.
	 * 
	 * @param eng
	 *            the engine to set the speed of
	 * @param speed
	 *            the speed
	 */
	public abstract void engineSpeedRelative(Engine eng, int speed);

	/**
	 * Initializes all relevant objects and opens any IO streams needed for
	 * communication.
	 */
	public abstract void start();

	/**
	 * Disposes of all relevant objects and closes any open IO streams. This method
	 * should be implemented under the assumption that {@code start()} may be called
	 * any time afterwards.
	 */
	public abstract void stop();

}
