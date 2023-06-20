package com.example.designpattern.command;


/*
* Command that does nothing
* */
public class NoCommand implements Command {
        @Override
        public void execute() {
        }

@Override
public void undo() {
}
}
