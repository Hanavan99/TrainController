package com.github.hanavan99.traincontroller.core;

import java.io.Serializable;

public class Route extends Resource implements Serializable {

	private static final long serialVersionUID = -7964307109564640713L;

	@Override
	public int getCommandBitmask() {
		return Commands.MASK_ROUTE;
	}

}
