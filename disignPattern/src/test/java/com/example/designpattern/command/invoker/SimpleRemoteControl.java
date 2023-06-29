package com.example.designpattern.command.invoker;

import com.example.designpattern.command.command.Command;

public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {}

    public void setCommand(Command command) { slot = command; }

    public void buttonWasPressed() { slot.execute(); }
}
