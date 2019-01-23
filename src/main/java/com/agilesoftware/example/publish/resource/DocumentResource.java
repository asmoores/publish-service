package com.agilesoftware.example.publish.resource;

import com.agilesoftware.example.publish.utils.ResponsePrinter;
import com.agilesoftware.rest.resteasy.utils.Logged;
import org.jboss.resteasy.annotations.ContentEncoding;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/document")
public class DocumentResource {
    private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    @GET
    @Path("{docId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Logged
    public Response get(@PathParam("docId") String id) {
        log.debug("Handling a docment GET request for document id {}", id);
        Client client = ClientBuilder.newClient();

        String uri = "http://localhost:9200/newindex/_doc/" + id;
        Response responseFromNoSQLStore = null;
        try {
            responseFromNoSQLStore = client.target(uri).request().get();
            String responseBodyAsString = ResponsePrinter.logResponseDetails(responseFromNoSQLStore);
            return Response.status(Response.Status.OK).entity(responseBodyAsString).build();
        } finally {
            try {
                log.debug("Closing resources...");
                if (responseFromNoSQLStore != null) responseFromNoSQLStore.close();
                if (client != null) client.close();
                log.debug("done.");
            } catch(Exception e) {
                log.error("failed cleanup", e);
            }
        }
    }

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
            if (responseFromNoSQLStore != null) responseFromNoSQLStore.close();
            if (client != null) client.close();
            log.debug("done.");
        }
    }
}
