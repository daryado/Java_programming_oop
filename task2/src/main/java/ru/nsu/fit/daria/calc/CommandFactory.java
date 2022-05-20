package ru.nsu.fit.daria.calc;

import ru.nsu.fit.daria.calc.exceptions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class CommandFactory {
    private final String CONFIG = "config.properties";
    private final Logger logger = getLogger("Logger");
    private Properties props = new Properties();
    private volatile static CommandFactory instance;
    private final Map<String, Command> commands = new HashMap<>();

    private CommandFactory() throws IOException {
            logger.info("Start create CommandFactory");
            InputStream  in = Main.class.getResourceAsStream(CONFIG);

            props.load(in);
            in.close();
            logger.info("Successful create");
    }

    public static CommandFactory getInstance() throws IOException {
        if (instance == null)
            synchronized (CommandFactory.class)
            {
                if (instance == null) {
                    instance = new CommandFactory();
                }
            }
        return instance;
    }

    public Command createOperation(String operationName) throws CommandException, CommandException {
        Command operation;
        try {
            Class<?> aClass = Class.forName(props.getProperty(operationName));
            operation = (Command)aClass.forName(props.getProperty(operationName)).getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            throw new CommandException();
        }
        return operation;
    }
}
