package ru.nsu.fit.daria.calc.commands;

import org.junit.Test;
import ru.nsu.fit.daria.calc.CommandFactory;
import ru.nsu.fit.daria.calc.Context;

import static junit.framework.TestCase.assertEquals;


public class plusTest {

    @Test
    public void addSmth() throws Exception {
        CommandFactory factory = CommandFactory.getInstance();
        Context ctx = new Context();
        String[] args = new String[0];

        Plus sum = new Plus();
        ctx.push(485.9);
        ctx.push(76.0);
        sum.action(args, ctx);
        assertEquals(561.9, ctx.pop());
    }
}