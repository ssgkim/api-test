package org.acme;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.logging.Logger;
import io.vertx.core.json.JsonObject;

@Path("/v1")
public class RestApiTest {
    private static final Logger LOG = Logger.getLogger(RestApiTest.class);

    @GET
    //@Produces(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test/{customerIndentifier}/update")
    public String test(@PathParam String customerIndentifier) throws IOException {
        CsvResource resource = CsvResource.getInstance();
        // Map<String, JsonObject> jsonDatas = resource.getJsonDatas();
        // System.out.println(jsonDatas);
        JsonObject jsonData = resource.getJsonData(customerIndentifier);
        //LOG.debug("");
        //LOG.info(jsonData.toString());
        System.out.println(jsonData.toString());
        return jsonData.toString();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "http://localhost:8080/v1/test/{customerIndentifier}/update";
    }
}
