package ru.nsu.fit.daria.calc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import static java.util.logging.Logger.getLogger;

public class Main {
    private final Logger logger = getLogger("Logger");

    public static void main(String[] args){
        BufferedReader in = null;

        try {
            if (args.length > 0) {
                in = new BufferedReader(new FileReader(args[0]));
            }
            else {
                in = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter \".\" for exit");
            }
        }
        catch ( FileNotFoundException exp) {
            System.out.println("Can't find file: " + args[0]);
            System.exit(2);
        }

        Calc calculator  = new Calc();
        try{
            calculator.calculate(in);
        }catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

}
