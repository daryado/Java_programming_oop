package ru.nsu.fit.daria.calc;


public interface Command {
    void action(String[] args, Context context) throws Exception;
}
