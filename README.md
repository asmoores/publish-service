
# README #

The Publish Service is a RESTful application implemented with RESTeasy.
It can be built and deployed with either maven or gradle.

Note.  Currently includes a GET endpoint to retrieve documents for testing purposes.

## A service to publish arbitrary JSON documents. ##

This service allows an arbitrary JSON document to be stored in a NoSQL datastore.

## How do I get set up? ##
### Install elasticsearch 
Use brew on the mac
```
brew tap elastic/tap
brew install elastic/tap/elasticsearch-full
brew install elastic/tap/kibana-full
```
If you are running a version of java later than 8 then there is a bug in elasticsearch config.  Change the `jvm.options` file
as follows, the full path to `gc.log` is required:
```
# JDK 9+ GC logging
9-:-Xlog:gc*,gc+age=trace,safepoint:file=/usr/local/var/log/elasticsearch/gc.log:utctime,pid,tags:filecount=32,filesize=64m
```
Start elastic:
```
brew services start elastic/tap/elasticsearch-full
```
Confirm its running:
``` bash
curl -X GET http://127.0.0.1:9200
```
Start kibana:
```bash
brew services start kibana-full
```
Check its running in a browser:
```
http://127.0.0.1:5601/
```
### Overview ###

Regardless of whether the service was built with Gradle or Maven it can be tested using httpie:

``` bash
> http localhost:8080/publish-service/health

HTTP/1.1 200 OK
Content-Length: 0
Date: Fri, 30 Nov 2018 19:55:07 GMT
Server: Jetty(9.2.24.v20180105)
```

or curl

``` sh
> curl -v localhost:8080/publish-service/health
*   Trying ::1...
* TCP_NODELAY set
* Connected to localhost (::1) port 8080 (#0)
> GET /publish-service/health HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Date: Fri, 30 Nov 2018 19:55:59 GMT
< Content-Length: 0
< Server: Jetty(9.2.24.v20180105)
<
* Connection #0 to host localhost left intact
```

### Using Maven ###

Build and deploy to Jetty using the Jetty plugin:

``` bash
mvn jetty:run
```

### Using Gradle ###

Build and deploy to Jetty using the Gretty plugin:

``` bash
./gradlew appRun
```

### Dependencies ###

Currently this service is dependent on elasticsearch as its NoSQL data store. It assumes
elasticsearch is listening on localhost:9200.

### How to run tests ###

TBA

### Deployment instructions ###

TBA