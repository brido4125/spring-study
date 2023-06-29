package com.example.designpattern.command.command;

import com.example.designpattern.command.receiver.Light;

public class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) { this.light = light; }

    public void execute() { light.on(); }

    public void undo() {
        light.off();
    }
}
