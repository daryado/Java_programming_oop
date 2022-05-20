package ru.nsu.fit.daria.calc.commands;

import org.junit.Test;
import ru.nsu.fit.daria.calc.CommandFactory;
import ru.nsu.fit.daria.calc.Context;

import static junit.framework.TestCase.assertEquals;

public class defineTest {

    @Test
    public void definePi() throws Exception {
        CommandFactory factory = CommandFactory.getInstance();
        Context ctx = new Context();

        Define def = new Define();
        Push Push = new Push();
        String[] args = {"define", "pi", "3.14"};
        def.action(args, ctx);
        Push.action(args, ctx);
        assertEquals(3.14, ctx.getStack().pop());
    }
}