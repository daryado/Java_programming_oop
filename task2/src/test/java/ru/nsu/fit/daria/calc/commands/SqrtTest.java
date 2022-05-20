package ru.nsu.fit.daria.calc.commands;

import org.junit.Test;
import ru.nsu.fit.daria.calc.CommandFactory;
import ru.nsu.fit.daria.calc.Context;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class SqrtTest {

    @Test
    public void SqrtSmth() throws Exception{
        CommandFactory factory = CommandFactory.getInstance();
        Context ctx = new Context();

        Sqrt cmd = new Sqrt();
        ctx.getStack().add(144.0);
        String[] args = new String[0];

        cmd.action(args, ctx);
        assertEquals(12.0, ctx.pop());
    }
}