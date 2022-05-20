package ru.nsu.fit.daria.calc.exceptions;

public class StackException extends Exception{
    public StackException() {
        super("Not enough items on the stack");
    }
}