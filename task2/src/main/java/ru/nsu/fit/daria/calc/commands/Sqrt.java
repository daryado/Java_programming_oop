package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.*;
import ru.nsu.fit.daria.calc.exceptions.*;

import java.util.logging.Logger;

import static java.lang.Math.sqrt;
import static java.util.logging.Logger.getLogger;

public class Sqrt implements Command {
    private final Logger logger = getLogger("Logger");

    Sqrt() {}

    @Override
    public void action(String[] args, Context ctx) throws Exception {
        double var, res;

        if (ctx.getStackSize() < 1) {
            throw new StackException();
        }

        var = ctx.pop();
        if (var < 0) {
            throw new SqrtException();
        } else {
            res = sqrt(var);

            logger.info("Root extraction result: " + res);
            ctx.push(res);
        }
    }
}
