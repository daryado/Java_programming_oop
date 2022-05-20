package ru.nsu.fit.daria.calc.exceptions;

public class DefineException extends CalculatorException{
    public DefineException() {
        super("Invalid second argument");
    }
}
