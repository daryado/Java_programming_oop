package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.Command;
import ru.nsu.fit.daria.calc.Context;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;


public class Define implements Command {
    Define(){}

    private final Logger logger = getLogger("Logger");

    @Override
    public void action(String[] args, Context ctx) throws Exception {
        ctx.getConstants().put(args[1], Double.valueOf(args[2]));
    }
}
