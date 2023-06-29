package com.example.designpattern.command.command;

import com.example.designpattern.command.receiver.GarageDoor;

public class GarageDoorOpenCommand implements Command {

    private final GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) { this.garageDoor = garageDoor; }

    public void execute() { garageDoor.up(); }

    public void undo() {
        garageDoor.down();
    }

}
