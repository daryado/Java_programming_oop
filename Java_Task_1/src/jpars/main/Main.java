package jpars.main;

import java.io.*;
import jpars.pars.Parser;

public class Main {
    public static void main(String[] args) throws IOException
    {
        Reader in = new InputStreamReader(new FileInputStream(args[0]));
        Writer out = new OutputStreamWriter(new FileOutputStream(args[1]));
        Parser.exec(in, out);

        in.close();
        out.close();
    }
}
