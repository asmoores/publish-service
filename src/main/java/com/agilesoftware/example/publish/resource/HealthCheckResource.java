package com.agilesoftware.example.publish.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/health")
public class HealthCheckResource {
    @GET
    @Produces("application/text")
    public Response health() {
        return Response.ok().build();
    }
}
