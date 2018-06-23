package com.github.hanavan99.controller.core;

public class Commands {

	public static final int MASK_SWITCH = 0x4000;
	public static final int MASK_ROUTE = 0xD000;
	public static final int MASK_ENGINE = 0x0000;
	public static final int MASK_TRAIN = 0xC000;
	public static final int MASK_ACCESSORY = 0x8000;

	public static final int SWITCH_THROW_THROUGH = 0x00;
	public static final int SWITCH_THROW_OUT = 0x1F;
	public static final int SWITCH_SET_ADDRESS = 0x2B;
	public static final int SWITCH_ASSIGN_ROUTE_THROUGH = 0x40;
	public static final int SWITCH_ASSIGN_ROUTE_OUT = 0x60;

	public static final int ROUTE_THROW = 0x1F;
	public static final int ROUTE_CLEAR = 0x2C;

	public static final int ENGINE_DIRECTION_FORWARD = 0x00;
	public static final int ENGINE_DIRECTION_TOGGLE = 0x01;
	public static final int ENGINE_DIRECTION_REVERSE = 0x03;
	public static final int ENGINE_SPEED_BOOST = 0x04;
	public static final int ENGINE_SPEED_BRAKE = 0x07;
	public static final int ENGINE_COUPLER_FRONT = 0x05;
	public static final int ENGINE_COUPLER_REAR = 0x06;
	public static final int ENGINE_HORN_1 = 0x1C;
	public static final int ENGINE_HORN_2 = 0x1F;
	public static final int ENGINE_BELL = 0x1D;
	public static final int ENGINE_LETOFF = 0x1E;
	public static final int ENGINE_AUX1_OFF = 0x08;
	public static final int ENGINE_AUX1_OPTION1 = 0x09;
	public static final int ENGINE_AUX1_OPTION2 = 0x0A;
	public static final int ENGINE_AUX1_ON = 0x0B;
	public static final int ENGINE_AUX2_OFF = 0x0C;
	public static final int ENGINE_AUX2_OPTION1 = 0x0D;
	public static final int ENGINE_AUX2_OPTION2 = 0x0E;
	public static final int ENGINE_AUX2_ON = 0x0F;
	public static final int ENGINE_NUMERIC_N = 0x10;
	
	public static final int ENGINE_ASSIGN_TRAIN = 0x30;
	public static final int ENGINE_ASSIGN_SINGLE_FORWARD = 0x20;
	public static final int ENGINE_ASSIGN_SINGLE_REVERSE = 0x24;
	public static final int ENGINE_ASSIGN_HEAD_FORWARD = 0x21;
	public static final int ENGINE_ASSIGN_HEAD_REVERSE = 0x25;
	public static final int ENGINE_ASSIGN_MIDDLE_FORWARD = 0x22;
	public static final int ENGINE_ASSIGN_MIDDLE_REVERSE = 0x26;
	public static final int ENGINE_ASSIGN_REAR_FORWARD = 0x23;
	public static final int ENGINE_ASSIGN_REAR_REVERSE = 0x27;
	public static final int ENGINE_MOMENTUM_LOW = 0x28;
	public static final int ENGINE_MOMENTUM_MEDIUM = 0x29;
	public static final int ENGINE_MOMENTUM_HIGH = 0x2A;
	public static final int ENGINE_SET_ADDRESS = 0x2B;

	public static final int ENGINE_SPEED_ABSOLUTE = 0x60;
	public static final int ENGINE_SPEED_RELATIVE = 0x40;
	
	public static final int ACC_AUX1_OFF = 0x08;
	public static final int ACC_AUX1_OPTION1 = 0x09;
	public static final int ACC_AUX1_OPTION2 = 0x0A;
	public static final int ACC_AUX1_ON = 0x0B;
	public static final int ACC_AUX2_OFF = 0x0C;
	public static final int ACC_AUX2_OPTION1 = 0x0D;
	public static final int ACC_AUX2_OPTION2 = 0x0E;
	public static final int ACC_AUX2_ON = 0x0F;
	public static final int ACC_NUMERIC_N = 0x10;
	
	public static final int ACC_ALL_OFF = 0x20;
	public static final int ACC_ALL_ON = 0x2F;
	public static final int ACC_SET_ADDRESS = 0x2B;
	public static final int ACC_ASSIGN_AUX1_GROUP = 0x20;
	public static final int ACC_ASSIGN_AUX2_GROUP = 0x20;
	
	public static final int GROUP_OFF = 0x08;
	public static final int GROUP_OPTION1 = 0x09;
	public static final int GROUP_OPTION2 = 0x0B;
	public static final int GROUP_ON = 0x0B;
	public static final int GROUP_CLEAR = 0x2C;
	
	public static final int SYSTEM_HALT = 0xFFFF;
	public static final int SYSTEM_NOP = 0xFF80;
	public static final int SYSTEM_RESERVED1 = 0xFEFF;
	public static final int SYSTEM_RESERVED2 = 0xFFFE;
	
}
