package com.github.hanavan99.controller.core;

import java.io.Serializable;

public class Train extends Resource implements Serializable {

	private static final long serialVersionUID = 5350453009094574036L;

	@Override
	public int getCommandBitmask() {
		return Commands.MASK_TRAIN;
	}

}
