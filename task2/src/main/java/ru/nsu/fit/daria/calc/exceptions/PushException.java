package ru.nsu.fit.daria.calc.exceptions;

public class PushException extends CalculatorException {
    public PushException() {
        super("Variable doesn't exist");
    }
}