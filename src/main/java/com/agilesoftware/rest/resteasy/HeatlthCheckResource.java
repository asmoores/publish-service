package com.agilesoftware.rest.resteasy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/health")
public class HeatlthCheckResource {
    @GET
    @Produces("application/text")
    public Response health() {
        return Response.ok().build();
    }
}
