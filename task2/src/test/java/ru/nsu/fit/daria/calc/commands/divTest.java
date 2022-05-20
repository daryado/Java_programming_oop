package ru.nsu.fit.daria.calc.commands;

import org.junit.Test;
import ru.nsu.fit.daria.calc.CommandFactory;
import ru.nsu.fit.daria.calc.Context;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class divTest {

    @Test
    public void divSmth() throws Exception {
        CommandFactory factory = CommandFactory.getInstance();
        Context ctx = new Context();

        Div cmd = new Div();
        ctx.getStack().add(2.0);
        ctx.getStack().add(100.0);
        cmd.action(null, ctx);
        assertEquals(50, ctx.getStack().pop());
    }
}