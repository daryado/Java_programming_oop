package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.*;
import ru.nsu.fit.daria.calc.exceptions.*;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Div implements Command {
    private final Logger logger = getLogger("Logger");
    Div(){}

    @Override
    public void action (String args[], Context ctx) throws Exception {
        double x, y, rez;
        if (ctx.getStackSize() < 2){
            throw new StackException();
        }

        x = ctx.pop();
        y = ctx.pop();
        if (y == 0) {
            ctx.push(x);
            ctx.push(y);
            throw new DivException();
        }
        rez = x/y;

        logger.info("Division result: " + rez);
        ctx.push(rez);
    }

}