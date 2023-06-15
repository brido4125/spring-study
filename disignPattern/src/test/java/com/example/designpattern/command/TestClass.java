package com.example.designpattern.command;

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
    void garageControlTest() {
        SimpleRemoteControl remote = new SimpleRemoteControl();//Invoker

        GarageDoor garageDoor = new GarageDoor();//Receiver
        Light light = new Light();//Receiver

        GarageDoorOpenCommand garageOpen = new GarageDoorOpenCommand(garageDoor);//ConcreteCommand
        LightOnCommand lightOn = new LightOnCommand(light);//ConcreteCommand

        /*
        * Command Design Pattern is used to encapsulate a request as an object.
        * Encapsulate a request meaning that parameterizing different requests.
        * In this case, a request is encapsulated as a Command object.
        * Concrete Command Instance means that the Invoker is coupled to the Concrete Command.
        * Also, the Receiver is coupled to the Concrete Command.
        * */
        remote.setCommand(lightOn);
        remote.buttonWasPressed();

        remote.setCommand(garageOpen);
        remote.buttonWasPressed();
    }
}
