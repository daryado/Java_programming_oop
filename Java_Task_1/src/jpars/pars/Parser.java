package jpars.pars;

import jpars.reader.Read;
import jpars.writer.Write;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.*;

public class Parser {
    public static void exec(Reader in, Writer out)throws IOException {
        String str = "";
        StringBuilder strBuilder = new StringBuilder();;
        Read.exec(in, strBuilder);
        str = strBuilder.toString();

        Map<String, Integer> map = new HashMap<>();;
        StringBuilder word = new StringBuilder();
        Integer count = 0, sum = 0;
        for (char c: str.toCharArray()){
            if (!Character.isLetterOrDigit(c)){
                if (!word.isEmpty()) {
                    count = (map.get(word.toString()) != null) ? map.get(word.toString()) : 0;
                    map.put(word.toString(), count + 1);
                    word.delete(0, word.capacity() + 1);
                    sum += 1;
                }
            } else {
                word.append(c);
            }
        }
        Write.exec(out, map, sum);
    }
}
