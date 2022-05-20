package ru.nsu.fit.daria.calc.commands;

import org.junit.Test;
import ru.nsu.fit.daria.calc.CommandFactory;
import ru.nsu.fit.daria.calc.Context;

import static junit.framework.TestCase.assertEquals;

public class subTest {

    @Test
    public void action() throws Exception{
        CommandFactory factory = CommandFactory.getInstance();
        Context ctx = new Context();

        Sub cmd = new Sub();
        ctx.getStack().add(10.0);
        ctx.getStack().add(224.0);
        String[] args = new String[0];

        cmd.action(args, ctx);
        assertEquals(214.0, ctx.pop());
    }
}