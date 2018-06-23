package com.github.hanavan99.traincontroller;

public enum CommandType {
    RouteClear(CommandSet.TMCC1Route, 1, 0x0C, "clear"),
    RouteThrow(CommandSet.TMCC1Route, 0, 0x1F, "throw"),
    SwitchAssignRouteThrough(CommandSet.TMCC1Switch, 2, -1, "assignRouteThrough"),
    SwitchAssignRouteOut(CommandSet.TMCC1Switch, 3, -1, "assignRouteOut"),
    SwitchThrowThrough(CommandSet.TMCC1Switch, 0, 0, "throwThrough"),
    SwitchThrowOut(CommandSet.TMCC1Switch, 0, 0x1F, "throwOut"),
    EngineCommandDirectionForward(CommandSet.TMCC1EngineRaw, 0x00, 0, "directionForward"),
    EngineCommandDirectionToggle(CommandSet.TMCC1EngineRaw, 0x01, 0, "directionToggle"),
    EngineCommandDirectionReverse(CommandSet.TMCC1EngineRaw, 0x03, 0, "directionReverse"),
    EngineCommandSpeedBoost(CommandSet.TMCC1EngineRaw, 0x04, 0, "speedBoost"),
    EngineCommandSpeedBrake(CommandSet.TMCC1EngineRaw, 0x07, 0, "speedBrake"),
    EngineCommandCouplerFront(CommandSet.TMCC1EngineRaw, 0x05, 0, "couplerFront"),
    EngineCommandCouplerRear(CommandSet.TMCC1EngineRaw, 0x06, 0, "couplerRear"),
    EngineCommandHorn1(CommandSet.TMCC1EngineRaw, 0x1c, 0, "horn1"),
    EngineCommandHorn2(CommandSet.TMCC1EngineRaw, 0x1f, 0, "horn2"),
    EngineCommandBell(CommandSet.TMCC1EngineRaw, 0x1d, 0, "bell"),
    EngineCommandLetoff(CommandSet.TMCC1EngineRaw, 0x1e, 0, "letoff"),
    EngineCommandAux1Off(CommandSet.TMCC1EngineRaw, 0x08, 0, "aux1Off"),
    EngineCommandAux1Option1(CommandSet.TMCC1EngineRaw, 0x09, 0, "aux1Option1"),
    EngineCommandAux1Option2(CommandSet.TMCC1EngineRaw, 0x0a, 0, "aux1Option2"),
    EngineCommandAux1On(CommandSet.TMCC1EngineRaw, 0x0b, 0, "aux1On"),
    EngineCommandAux2Off(CommandSet.TMCC1EngineRaw, 0x0c, 0, "aux2Off"),
    EngineCommandAux2Option1(CommandSet.TMCC1EngineRaw, 0x0d, 0, "aux2Option1"),
    EngineCommandAux2Option2(CommandSet.TMCC1EngineRaw, 0x0e, 0, "aux2Option2"),
    EngineCommandAux2On(CommandSet.TMCC1EngineRaw, 0x0f, 0, "aux2On"),
    EngineCommandNumericN(CommandSet.TMCC1EngineExtraCommandBit, 0, -1, "numericN"),
    EngineCommandAssignTrain(CommandSet.TMCC1EngineExtraCommandBit, 3, -1, "assignTrain"),
    EngineCommandAssignSingleForward(CommandSet.TMCC1EngineRaw, 0x20, 0, "assignSingleForward"),
    EngineCommandAssignSingleReverse(CommandSet.TMCC1EngineRaw, 0x24, 0, "assignSingleReverse"),
    EngineCommandAssignHeadForward(CommandSet.TMCC1EngineRaw, 0x21, 0, "assignHeadForward"),
    EngineCommandAssignHeadReverse(CommandSet.TMCC1EngineRaw, 0x25, 0, "assignHeadReverse"),
    EngineCommandAssignMiddleForward(CommandSet.TMCC1EngineRaw, 0x22, 0, "assignMiddleForward"),
    EngineCommandAssignMiddleReverse(CommandSet.TMCC1EngineRaw, 0x26, 0, "assignMiddleReverse"),
    EngineCommandAssignRearForward(CommandSet.TMCC1EngineRaw, 0x23, 0, "assignRearForward"),
    EngineCommandAssignRearReverse(CommandSet.TMCC1EngineRaw, 0x27, 0, "assignRearReverse"),
    EngineCommandMomentumLow(CommandSet.TMCC1EngineRaw, 0x28, 0, "momentumLow"),
    EngineCommandMomentumMedium(CommandSet.TMCC1EngineRaw, 0x29, 0, "momentumMedium"),
    EngineCommandMomentumHigh(CommandSet.TMCC1EngineRaw, 0x2a, 0, "momentumHigh"),
    EngineCommandSetAddress(CommandSet.TMCC1EngineRaw, 0x2b, 0, "setAddress"),
    EngineCommandSpeedAbsolute(CommandSet.TMCC1Engine, 3, -1, "speedAbsolute"),
    EngineCommandSpeedRelative(CommandSet.TMCC1EngineExtraCommandBit, 4, -1, "speedRelative");

    private final CommandSet set;
    private final int command;
    private final int data;
    private final String friendlyName;

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

    public String getFriendlyName() {
        return friendlyName;
    }

    CommandType(CommandSet set, int command, int data, String friendlyName) {
        this.set = set;
        this.command = command;
        this.data = data;
        this.friendlyName = friendlyName;
    }
}
