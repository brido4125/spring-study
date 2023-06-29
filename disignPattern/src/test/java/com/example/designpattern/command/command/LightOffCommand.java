package com.example.designpattern.command.command;

import com.example.designpattern.command.receiver.Light;

public class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) { this.light = light; }

    public void execute() { light.off(); }

    public void undo() {
        light.on();
    }
}
