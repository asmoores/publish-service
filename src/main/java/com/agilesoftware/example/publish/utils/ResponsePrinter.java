package com.agilesoftware.example.publish.utils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class ResponsePrinter {
    private static final Logger log = LoggerFactory.getLogger(ResponsePrinter.class);

    public static String logResponseDetails(Response response) {
        String responseBodyAsString = response.readEntity(String.class);
        log.debug("Response is: \n{}-{} \n{} \n{} bytes \n{} \n{} chars long",
                response.getStatus(),
                response.getStatusInfo(),
                response.getMediaType(),
                response.getLength(),
                new JSONObject(responseBodyAsString).toString(4),
                responseBodyAsString.length());
        return responseBodyAsString;
    }
}
