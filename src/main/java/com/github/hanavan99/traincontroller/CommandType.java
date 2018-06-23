package com.github.hanavan99.traincontroller;

public enum CommandType {
    RouteClear(CommandSet.TMCC1Route, 1, 0x0C),
    RouteThrow(CommandSet.TMCC1Route, 0, 0x1F),
    SwitchAssignRouteThrough(CommandSet.TMCC1Switch, 2, -1),
    SwitchAssignRouteOut(CommandSet.TMCC1Switch, 3, -1),
    SwitchThrowThrough(CommandSet.TMCC1Switch, 0, 0),
    SwitchThrowOut(CommandSet.TMCC1Switch, 0, 0x1F);

    private final CommandSet set;
    private final int command;
    private final int data;

    public int formatCommand(int address, int data) {
        if (data < 0) {
            if (this.data >= 0) {
                data = this.data;
            } else {
                throw new IllegalArgumentException("Data has to be provided");
            }
        } else if (this.data >= 0) {
            throw new IllegalArgumentException("This command does not take data");
        }
        return set.formatCommand(address, command, data);
    }

    public int formatCommand(int address) {
        return formatCommand(address, -1);
    }

    CommandType(CommandSet set, int command, int data) {
        this.set = set;
        this.command = command;
        this.data = data;
    }
}
