package com.github.hanavan99.traincontroller;

public enum CommandSet {
    TMCC1Switch(0xFE8000, 7, 7, 5, 2, 0, 5),
    TMCC1Route(0xFED000, 7, 5, 5, 2, 0, 5),
    TMCC1Engine(0xFE0000, 7, 7, 5, 2, 0, 5);

    private final int constant;
    private final int address_start;
    private final int address_length;
    private final int command_start;
    private final int command_length;
    private final int data_start;
    private final int data_length;

    public int formatCommand(int address, int command, int data) {
        return constant
            | ((address & ((1 << address_length) - 1)) << address_start)
            | ((command & ((1 << command_length) - 1)) << command_start)
            | ((command & ((1 << data_length) - 1)) << data_start);
    }

    CommandSet(int constant, int address_start, int address_length, int command_start, int command_length, int data_start, int data_length) {
        this.constant = constant;
        this.address_start = address_start;
        this.address_length = address_length;
        this.command_start = command_start;
        this.command_length = command_length;
        this.data_start = data_start;
        this.data_length = data_length;
    }
}
