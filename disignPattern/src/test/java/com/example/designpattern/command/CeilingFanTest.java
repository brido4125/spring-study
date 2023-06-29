package com.example.designpattern.command;

import com.example.designpattern.command.command.ceilingfan.CeilingFanHighCommand;
import com.example.designpattern.command.command.ceilingfan.CeilingFanLowCommand;
import com.example.designpattern.command.command.ceilingfan.CeilingFanMediumCommand;
import com.example.designpattern.command.command.ceilingfan.CeilingFanOffCommand;
import com.example.designpattern.command.invoker.RemoteControlWithUndo;
import com.example.designpattern.command.receiver.CeilingFan;
import org.junit.jupiter.api.Test;

public class CeilingFanTest {
    @Test
    void test() {
        RemoteControlWithUndo remote = new RemoteControlWithUndo();

        CeilingFan ceilingFan = new CeilingFan("Living Room");

        CeilingFanHighCommand ceilingFanHigh = new CeilingFanHighCommand(ceilingFan);
        CeilingFanMediumCommand ceilingFanMedium = new CeilingFanMediumCommand(ceilingFan);
        CeilingFanLowCommand ceilingFanLow = new CeilingFanLowCommand(ceilingFan);
        CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(ceilingFan);

        remote.setCommand(0, ceilingFanMedium, ceilingFanOff);
        remote.setCommand(1, ceilingFanHigh, ceilingFanOff);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);

        remote.undoButtonWasPushed();

        remote.onButtonWasPushed(1);
        remote.undoButtonWasPushed();
    }
}
