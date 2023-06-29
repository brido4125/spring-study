package com.example.designpattern.command.command.ceilingfan;

import com.example.designpattern.command.receiver.CeilingFan;

public class CeilingFanMediumCommand extends CeilingFanCommand {
    public CeilingFanMediumCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        setPrevSpeed(); // remember previous state
        ceilingFan.medium();
    }
}
