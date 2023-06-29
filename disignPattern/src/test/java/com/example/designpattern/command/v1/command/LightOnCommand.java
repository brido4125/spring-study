package com.example.designpattern.command.v1.command;

import com.example.designpattern.command.v1.receiver.Light;

public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) { this.light = light; }

    public void execute() { light.on(); }

    public void undo() {
        light.off();
    }
}
