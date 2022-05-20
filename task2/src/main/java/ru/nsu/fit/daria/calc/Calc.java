package ru.nsu.fit.daria.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Calc {

    private final Logger logger = getLogger("Logger");

    private final String WORD_SEPARATOR = " ";
    private final String COMMENT_MARK = "#";

    private Context ctx = new Context();

    public Calc() {}

    public Context getContext() { return ctx;}

    public void calculate(BufferedReader in) throws IOException, Exception {
        logger.info("counting...");
        CommandFactory operationFactory = CommandFactory.getInstance();

        String str;
        while ((str = in.readLine()) != null) {
            if (str.startsWith(COMMENT_MARK)) {
                continue;
            }
                var words = str.split(WORD_SEPARATOR);
                var cmd = operationFactory.createOperation(words[0]);

                var args = new String[words.length - 1];
                if (words.length - 1 >= 0) {
                    System.arraycopy(words, 1, args, 0, words.length - 1);
                    cmd.action(args, ctx);
                }
        }
        logger.info("counting completed");
    }

}
