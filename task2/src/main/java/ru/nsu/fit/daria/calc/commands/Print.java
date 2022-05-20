package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.*;
import ru.nsu.fit.daria.calc.exceptions.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Print implements Command {
    private final Logger logger = getLogger("Logger");

    Print(){}

    @Override
    public void action(String[] args, Context ctx) throws Exception {
        if (ctx.getStackSize() < 1) {
            throw new StackException();
        }

        System.out.println(ctx.getStack().peek());
        logger.info("Printed successfully");
    }
}