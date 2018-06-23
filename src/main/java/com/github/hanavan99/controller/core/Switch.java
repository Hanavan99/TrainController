package com.github.hanavan99.controller.core;

import java.io.Serializable;

public class Switch extends Resource implements Serializable {

	private static final long serialVersionUID = -304432075471482899L;

	public Switch(int id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return getName() + " (ID " + getID() + ")";
	}

	@Override
	public int getCommandBitmask() {
		return Commands.MASK_SWITCH;
	}

}
