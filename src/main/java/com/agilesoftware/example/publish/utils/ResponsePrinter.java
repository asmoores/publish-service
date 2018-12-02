package com.agilesoftware.example.publish.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class ResponsePrinter {
    private static final Logger log = LoggerFactory.getLogger(ResponsePrinter.class);

    public static void logResponseDetails(Response response) {
        log.debug("Reponse is: \n {} \n {}",
                response.getStatus(),
                response.getStatusInfo());

    }
}
