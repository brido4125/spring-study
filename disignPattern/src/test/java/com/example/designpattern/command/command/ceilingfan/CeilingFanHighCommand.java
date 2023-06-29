package com.example.designpattern.command.command.ceilingfan;

import com.example.designpattern.command.receiver.CeilingFan;

public class CeilingFanHighCommand extends CeilingFanCommand {
    public CeilingFanHighCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        setPrevSpeed(); // remember previous state
        ceilingFan.high();
    }
}
