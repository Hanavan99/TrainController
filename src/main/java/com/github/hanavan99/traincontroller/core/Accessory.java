package com.github.hanavan99.traincontroller.core;

import java.io.Serializable;

public class Accessory extends Resource implements Serializable {

	private static final long serialVersionUID = 1332400235900427975L;

	@Override
	public int getCommandBitmask() {
		return Commands.MASK_ACCESSORY;
	}

}
