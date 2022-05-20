package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.Command;
import ru.nsu.fit.daria.calc.Context;
import ru.nsu.fit.daria.calc.exceptions.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Pop implements Command {

    private final Logger logger = getLogger("Logger");

    Pop() {}

    @Override
    public void action(String[] args, Context ctx) throws Exception {
        if (ctx.getStackSize() < 1) {
            throw new StackException();
        }

        double value = ctx.pop();

        logger.info("Popped value: " + value);
    }
}