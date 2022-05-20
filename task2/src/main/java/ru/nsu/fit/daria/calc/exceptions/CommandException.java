package ru.nsu.fit.daria.calc.exceptions;

public class CommandException extends Exception{
    public CommandException() {
        super("Command not found");
    }
}