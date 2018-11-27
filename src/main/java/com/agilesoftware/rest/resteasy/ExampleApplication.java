package com.agilesoftware.rest.resteasy;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resteasy-example")
public class ExampleApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public ExampleApplication() {
        singletons.add(new ExampleResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
