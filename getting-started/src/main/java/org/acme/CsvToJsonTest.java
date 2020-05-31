package org.acme;

import java.io.*;
import java.util.*;
//import com.fasterxml.jackson.databind.*;

public class CsvToJsonTest {
    public static void main(String args[]) throws Exception {
        File input = new File("input.csv");
        //List<List<String>> rows = new ArrayList<>();
        try (InputStream in = new FileInputStream(input);) {
            CSV csv = new CSV(true, ',', in);
            List<String> fieldNames = null;
            if (csv.hasNext())
                fieldNames = new ArrayList<>(csv.next());
            List<Map<String, String>> list = new ArrayList<>();
            while (csv.hasNext()) {
                List<String> x = csv.next();
                Map<String, String> obj = new LinkedHashMap<>();
                for (int i = 0; i < fieldNames.size(); i++) {
                    obj.put(fieldNames.get(i), x.get(i));
                }
                list.add(obj);
            }
            //ObjectMapper mapper = new ObjectMapper();
            // mapper.enable(SerializationFeature.INDENT_OUTPUT);
            //mapper.writeValue(System.out, list);
        }
    }
}