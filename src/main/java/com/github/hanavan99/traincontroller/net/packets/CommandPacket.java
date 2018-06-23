package com.github.hanavan99.traincontroller.net.packets;

import java.io.Serializable;

import com.github.hanavan99.traincontroller.core.Resource;
import com.github.hanavan99.traincontroller.core.User;
import com.github.hanavan99.traincontroller.core.enums.CommandType;

public class CommandPacket extends Packet implements Serializable {

	private static final long serialVersionUID = -1492622020150286242L;

	private CommandType type;
	private Resource arg;
	private int command;
	private User user;

	public CommandPacket(CommandType type, Resource arg, int command, User user) {
		this.type = type;
		this.arg = arg;
		this.command = command;
		this.user = user;
	}

	public CommandType getType() {
		return type;
	}

	public Resource getArg() {
		return arg;
	}

	public int getCommand() {
		return command;
	}

	public User getUser() {
		return user;
	}

	public int toIntCommand() {
		return arg.getCommandBitmask() | (arg.getID() << 7) | command;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof CommandPacket) {
			CommandPacket packet = (CommandPacket) other;
			return toIntCommand() == packet.toIntCommand();
		}
		return false;
	}

}
