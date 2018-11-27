package com.agilesoftware.rest.resteasy;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Path("/example")
public class ExampleResource {

    @GET
    @Produces("application/text")
    public String getAResource() {
        return "hello";
    }

    @POST
    @Path("{index}")
    public void saveAResource(@PathParam("index") String index) {
        Client client = ClientBuilder.newClient();

        String uri = "http://localhost:9200/" + index + "/_doc/3";

        Response response = client.target(uri).request().post(Entity.json("{\n" +
                "    \"user\" : \"andrew\",\n" +
                "    \"post_date\" : \"2018-11-15T14:12:12\",\n" +
                "    \"message\" : \"trying out Elasticsearch from resteasy\"\n" +
                "}"));
        if (response.getStatus() != 201) {
            throw new RuntimeException("could not create resource");
        }
    }
}
