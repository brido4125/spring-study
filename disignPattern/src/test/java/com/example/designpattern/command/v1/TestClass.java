package com.example.designpattern.command.v1;

import com.example.designpattern.command.v1.command.GarageDoorOpenCommand;
import com.example.designpattern.command.v1.command.LightOnCommand;
import com.example.designpattern.command.v1.invoker.SimpleRemoteControl;
import com.example.designpattern.command.v1.receiver.GarageDoor;
import com.example.designpattern.command.v1.receiver.Light;
import org.junit.jupiter.api.Test;

public class TestClass {
    @Test
    void remoteControlTest() {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        LightOnCommand lightOn = new LightOnCommand(light);

        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }

    @Test
    void remoteControlTest2() {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        LightOnCommand lightOn = new LightOnCommand(light);
        GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor);

        remote.setCommand(lightOn);
        remote.buttonWasPressed();

        remote.setCommand(garageOpen);
        remote.buttonWasPressed();
    }
}
