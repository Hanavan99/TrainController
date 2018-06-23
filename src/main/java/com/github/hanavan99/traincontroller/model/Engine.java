package com.github.hanavan99.traincontroller.model;

import java.io.IOException;

import com.github.hanavan99.traincontroller.CommandQueue;
import com.github.hanavan99.traincontroller.CommandType;
import com.github.hanavan99.traincontroller.TopicNames;
import com.rabbitmq.client.Channel;

public class Engine extends CommandableObject {
    private static final CommandType[] COMMANDS = {
        CommandType.EngineCommandDirectionForward,
        CommandType.EngineCommandDirectionToggle,
        CommandType.EngineCommandDirectionReverse,
        CommandType.EngineCommandSpeedBoost,
        CommandType.EngineCommandSpeedBrake,
        CommandType.EngineCommandCouplerFront,
        CommandType.EngineCommandCouplerRear,
        CommandType.EngineCommandHorn1,
        CommandType.EngineCommandHorn2,
        CommandType.EngineCommandBell,
        CommandType.EngineCommandLetoff,
        CommandType.EngineCommandAux1Off,
        CommandType.EngineCommandAux1Option1,
        CommandType.EngineCommandAux1Option2,
        CommandType.EngineCommandAux1On,
        CommandType.EngineCommandAux2Off,
        CommandType.EngineCommandAux2Option1,
        CommandType.EngineCommandAux2Option2,
        CommandType.EngineCommandAux2On,
        CommandType.EngineCommandNumericN,
        CommandType.EngineCommandAssignTrain,
        CommandType.EngineCommandAssignSingleForward,
        CommandType.EngineCommandAssignSingleReverse,
        CommandType.EngineCommandAssignHeadForward,
        CommandType.EngineCommandAssignHeadReverse,
        CommandType.EngineCommandAssignMiddleForward,
        CommandType.EngineCommandAssignMiddleReverse,
        CommandType.EngineCommandAssignRearForward,
        CommandType.EngineCommandAssignRearReverse,
        CommandType.EngineCommandMomentumLow,
        CommandType.EngineCommandMomentumMedium,
        CommandType.EngineCommandMomentumHigh,
        CommandType.EngineCommandSetAddress,
        CommandType.EngineCommandSpeedAbsolute,
        CommandType.EngineCommandSpeedRelative
    };

    @Override
    public void registerAll() throws IOException {
        super.registerAll();
        for (CommandType cmd : COMMANDS) {
            register(new EngineCommandConsumer(getChannel(), this, TopicNames.ENGINE_COMMAND, cmd) {
                @Override
                public void handle(CommandQueue queue, CommandType command, int address, int data) throws IOException {
                    queue.runOnce(command, address, data);
                }
            });
            register(new EngineCommandConsumer(getChannel(), this, TopicNames.ENGINE_START_COMMAND, cmd) {
                @Override
                public void handle(CommandQueue queue, CommandType command, int address, int data) throws IOException {
                    queue.start(command, address, data);
                }
            });
            register(new EngineCommandConsumer(getChannel(), this, TopicNames.ENGINE_END_COMMAND, cmd) {
                @Override
                public void handle(CommandQueue queue, CommandType command, int address, int data) throws IOException {
                    queue.stop(command, address, data);
                }
            });
        }
    }

    public Engine(Channel channel, EngineList list, String name, CommandQueue cmd) throws IOException {
        super(channel, list, name, cmd);
    }
}
