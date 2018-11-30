package com.agilesoftware.rest.resteasy;

import com.agilesoftware.rest.resteasy.utils.ResponsePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Path("/document")
public class DocumentResource {
    private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    @POST
    @Path("{index}")
    public Response saveDocument(@PathParam("index") String index) {
        log.debug("Handling a document POST request.");
        Client client = ClientBuilder.newClient();

        String uri = "http://localhost:9200/" + index + "/_doc";
        Response responseFromNoSQLStore = null;
        try {
            responseFromNoSQLStore = client.target(uri).request().post(Entity.json("{\n" +
                    "    \"user\" : \"andrew\",\n" +
                    "    \"post_date\" : \"2018-11-15T14:12:12\",\n" +
                    "    \"message\" : \"trying out Elasticsearch from resteasy\"\n" +
                    "}"));

            ResponsePrinter.logResponseDetails(responseFromNoSQLStore);
            log.debug("Call to NoSQL data store returned {}", responseFromNoSQLStore);

            if (responseFromNoSQLStore.getStatus() != Response.Status.CREATED.getStatusCode()) {
                log.debug("Converting http error from NoSQL API to a WebApplicationException");
                throw new WebApplicationException("Could not create resource", Response.Status.INTERNAL_SERVER_ERROR);
            }

            return Response.status(Response.Status.CREATED).build();
        } finally {
            log.debug("Closing resources...");
            responseFromNoSQLStore.close();
            client.close();
            log.debug("done.");
        }
    }
}
