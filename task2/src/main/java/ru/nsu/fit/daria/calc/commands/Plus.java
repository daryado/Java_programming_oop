package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.Command;
import ru.nsu.fit.daria.calc.Context;
import ru.nsu.fit.daria.calc.exceptions.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;


public class Plus implements Command  {
    Plus(){}

    private final Logger logger = getLogger("Logger");

    @Override
    public void action(String[] args, Context ctx) throws Exception {
        double x, y, res;

        if (ctx.getStackSize() < 2) {
            throw new StackException();
        }

        y = ctx.pop();
        x = ctx.pop();
        res = x + y;

        logger.info("sum result: " + res);
        ctx.push(res);
    }
}
