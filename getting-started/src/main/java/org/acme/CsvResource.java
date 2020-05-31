package org.acme;

import java.io.*;
import java.util.*;
import org.jboss.logging.Logger;
import io.vertx.core.json.JsonObject;

public class CsvResource {

    private static final Logger LOG = Logger.getLogger(CsvResource.class);

    private static CsvResource resource;

    private static Map<String, JsonObject> jsonDatas = null;

    private CsvResource() {
        csvToJson();
    }

    public static CsvResource getInstance() {
        if (resource == null) {
            resource = new CsvResource();
        }
        return resource;
    }

    public Map<String, JsonObject> getJsonDatas() {
        return jsonDatas;
    }

    private static void csvToJson() {
        Map<String, JsonObject> tempDatas = new LinkedHashMap<>();
        try (InputStream in = CsvResource.class.getResourceAsStream("/input.csv");) {
            final CSV csv = new CSV(true, ',', in);
            List<String> fieldNames = null;
            if (csv.hasNext())
                fieldNames = new ArrayList<>(csv.next());

            while (csv.hasNext()) {
                final List<String> x = csv.next();
                JsonObject jsonData = new JsonObject();
                for (int i = 0; i < fieldNames.size(); i++) {
                    jsonData.put(fieldNames.get(i), x.get(i));
                }
                tempDatas.put(x.get(0), jsonData);
                
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }
        jsonDatas = tempDatas;
        LOG.info("message");
    }

    public JsonObject getJsonData(String customerIndentifier) {
        if (jsonDatas != null && jsonDatas.containsKey(customerIndentifier)) {
            return jsonDatas.get(customerIndentifier);
        }
        return new JsonObject();
    }

}