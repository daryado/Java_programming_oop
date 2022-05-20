package ru.nsu.fit.daria.calc.commands;

import ru.nsu.fit.daria.calc.Command;
import ru.nsu.fit.daria.calc.Context;
import ru.nsu.fit.daria.calc.exceptions.CalculatorException;

import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Push implements Command {
    private final Logger logger = getLogger("Logger");
    Push() {}

    @Override
    public void action(String[] args, Context ctx) throws CalculatorException {
        double value = ctx.getConstants().get(args[1]);

        if (Character.isAlphabetic(args[1].charAt(0))){
            ctx.getStack().add(ctx.getConstants().get(args[1]));
        } else {
            ctx.getStack().add(Double.valueOf(args[1]));
        }
            logger.info("Pushed value: " + value);
        }
}
