package com.github.hanavan99.traincontroller.core;

import java.io.Serializable;

import com.github.hanavan99.traincontroller.core.enums.EngineType;

public class Engine extends Resource implements Serializable {

	private static final long serialVersionUID = 4897225402465582210L;

	private String roadName;
	private String roadNumber;
	private String color;
	private EngineType type;
	private boolean inUse;

	public Engine(int id) {
		super(id);
	}

	public Engine(int id, String name) {
		super(id, name);
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadNumber() {
		return roadNumber;
	}

	public void setRoadNumber(String roadNumber) {
		this.roadNumber = roadNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public EngineType getType() {
		return type;
	}

	public void setType(EngineType type) {
		this.type = type;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	@Override
	public String toString() {
		return getName() + " (ID " + getID() + ")";
	}

	@Override
	public int getCommandBitmask() {
		return Commands.MASK_ENGINE;
	}

}
