package ru.nsu.fit.daria.calc.commands;

import org.junit.Test;
import ru.nsu.fit.daria.calc.CommandFactory;
import ru.nsu.fit.daria.calc.Context;

import static junit.framework.TestCase.assertEquals;

public class mulTest {

    @Test
    public void mulSmth() throws Exception{
        CommandFactory factory = CommandFactory.getInstance();
        Context ctx = new Context();

        Mul cmd = new Mul();
        ctx.getStack().add(10.0);
        ctx.getStack().add(224.0);
        String[] args = new String[0];

        cmd.action(args, ctx);
        assertEquals(2240.0, ctx.pop());
    }
}