package jpars.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Read {
    public static void exec(Reader in,StringBuilder strBuilder)throws IOException {
        BufferedReader bufferedReader = new BufferedReader(in);


        String line;
        while ((line = bufferedReader.readLine()) != null) {
            strBuilder.append(line);
        }

        }

}
