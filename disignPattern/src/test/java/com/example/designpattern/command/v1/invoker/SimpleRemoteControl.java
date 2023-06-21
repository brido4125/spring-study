package com.example.designpattern.command.v1.invoker;

import com.example.designpattern.command.v1.command.Command;

public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) { slot = command; }

    public void buttonWasPressed() { slot.execute(); }
}
