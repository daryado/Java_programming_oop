package jpars.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class Write {
    public static void PrintMap(){}


    public static void exec(Writer out, Map NewMap, Integer count)throws IOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter(out)) {
            List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(NewMap.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                    return ( b.getValue() - a.getValue() );
                }
            });
            for ( Map.Entry<String, Integer> me : list )
                bufferedWriter.write(me.getKey() + "; " + me.getValue() + "; " + (double)(me.getValue() * 10000 /count)/100 + "% " + "\n");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
