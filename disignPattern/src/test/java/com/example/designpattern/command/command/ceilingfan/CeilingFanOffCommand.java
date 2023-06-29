package com.example.designpattern.command.command.ceilingfan;

import com.example.designpattern.command.receiver.CeilingFan;

public class CeilingFanOffCommand extends CeilingFanCommand {
    public CeilingFanOffCommand(CeilingFan ceilingFan) {
        super(ceilingFan);
    }

    @Override
    public void execute() {
        setPrevSpeed(); // remember previous state
        ceilingFan.off();
    }
}
