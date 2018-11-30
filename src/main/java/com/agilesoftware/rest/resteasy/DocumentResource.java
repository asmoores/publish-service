package com.agilesoftware.rest.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Path("/document")
public class DocumentResource {

    @POST
    @Path("{index}")
    public void saveDocument(@PathParam("index") String index) {
        Client client = ClientBuilder.newClient();

        String uri = "http://localhost:9200/" + index + "/_doc/3";

        Response response = client.target(uri).request().post(Entity.json("{\n" +
                "    \"user\" : \"andrew\",\n" +
                "    \"post_date\" : \"2018-11-15T14:12:12\",\n" +
                "    \"message\" : \"trying out Elasticsearch from resteasy\"\n" +
                "}"));

        response.close();
        client.close();

        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            throw new RuntimeException("could not create resource");
        }
    }
}
