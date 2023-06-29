package com.example.designpattern.command.command;

import com.example.designpattern.command.receiver.Stereo;

public class StereoOnWithCDCommand implements Command{
    private final Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) { this.stereo = stereo; }

    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    public void undo() {
        stereo.off();
    }
}
