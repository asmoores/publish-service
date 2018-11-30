# README #

The Publish Service is a RESTful application implemented with RESTeasy.
It can be built and deployed with either maven or gradle.

### A service to allow CRUD on arbitrary JSON documents. ###

This service allows an arbitrary JSON document to be stored in a NoSQL datastore.


### How do I get set up? ###

Regardless of whether the service was built with Gradle or Maven it can be tested using httpie:

``` bash
> http localhost:8080/resteasy-example/example

HTTP/1.1 200 OK
Content-Length: 66
Content-Type: application/text
Date: Fri, 30 Nov 2018 19:35:41 GMT
Server: Jetty(9.4.14.v20181114)

hello, this is the ExampleResource responding to your GET request.
```

or curl

``` sh
curl -v localhost:8080/resteasy-example/example

*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /resteasy-example/example HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Date: Fri, 30 Nov 2018 19:36:47 GMT
< Content-Type: application/text
< Content-Length: 66
< Server: Jetty(9.4.14.v20181114)
<
* Connection #0 to host localhost left intact
hello, this is the ExampleResource responding to your GET request.
```

#### Using Maven ####

Build and deploy to Jetty using the Jetty plugin:

``` bash
mvn jetty:run
```



* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact