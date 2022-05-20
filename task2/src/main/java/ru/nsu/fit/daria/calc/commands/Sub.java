package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.Command;
import ru.nsu.fit.daria.calc.Context;
import ru.nsu.fit.daria.calc.exceptions.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Sub implements Command {
    private final Logger logger = getLogger("Logger");

    Sub(){}

    @Override
    public void action(String[] args, Context ctx) throws Exception {
        double x, y, res;

        if (ctx.getStackSize() < 2) {
            throw new StackException();
        }

        x = ctx.pop();
        y = ctx.pop();
        res = x - y;

        logger.info("Subtraction result: " + res);
        ctx.push(res);
    }
}
