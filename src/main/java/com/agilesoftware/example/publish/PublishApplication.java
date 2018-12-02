package com.agilesoftware.example.publish;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/publish-service")
public class PublishApplication extends Application {

    // -- Have the getSingletons and getClasses methods return empty sets and
    //    the servlet container will scan for classes annotated with Path or Provider

//    private Set<Object> singletons = new HashSet<Object>();
//
//    public PublishApplication() {
//        singletons.add(new DocumentResource());
//    }
//
//    @Override
//    public Set<Object> getSingletons() {
//        return singletons;
//    }
}
