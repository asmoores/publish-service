package com.agilesoftware.rest.resteasy.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

@Logged
@Provider
public class RequestLoggingFilter implements ContainerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingFilter.class);

    public void filter(ContainerRequestContext requestContext) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(requestContext.getEntityStream(), writer, StandardCharsets.UTF_8.name());
        String theString = writer.toString();
        LOG.info("filter entity {}", theString);
        // Use the ContainerRequestContext to extract information from the HTTP request
        // Information such as the URI, headers and HTTP entity are available
    }
}