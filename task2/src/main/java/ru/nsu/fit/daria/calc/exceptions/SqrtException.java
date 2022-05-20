package ru.nsu.fit.daria.calc.exceptions;

public class SqrtException extends CalculatorException{
    public SqrtException(){
        super("Negative value");
    }
}