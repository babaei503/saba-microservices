# Spring Boot Microservices

You can run build-all.bat in windows to build all project.
you can run start-infra-micros.bat and start-busns-micros.bat in windows 
to run all projects.

## Eureka Server

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run"). It will start up on port
8761,8762,8763 and serve the Eureka API from "/eureka".

We want to use the Peer Awareness feature that comes with the Eureka 
Service Registry. Basically, we’d like to have 3 registry instances 
that can share with each other the contents of the registry (the 
registered clients) so we implement resiliency.

Before you execute this application you need an extra step though. 
The Peer Awareness feature won’t work if you use the same hostname. 
We set them to eureka1,eureka2 and eureka3 in our configuration, but we 
need to redirect those aliases to our real local host for that to 
work on our machine. In windows, you need to modify your hosts file 
and add a couple of lines like these:

```
127.0.0.1 eureka1
127.0.0.1 eureka2
127.0.0.1 eureka3
```

Now you can run 3 times this application, again from your preferred 
IDE or from the command line, using for each execution a different profile:

```
mvn spring-boot:run -Dspring-boot.run.profiles=peer1
mvn spring-boot:run -Dspring-boot.run.profiles=peer2
mvn spring-boot:run -Dspring-boot.run.profiles=peer3
```

The Eureka dashboard will be accessible at 

```
http://localhost:8761
http://localhost:8762
http://localhost:8763
```

We secure the Eureka server simply by adding Spring Security
the default user name and password is admin,admin

## Product Service

This service responsible for handling information regarding products

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see the Product service

## Product Review Service

This service responsible for handling information regarding reviews

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see Product Review service

## Product Recommendation Service

This service responsible for handling information regarding recommendations

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see Product Recommendation service

## Product Composite Service

This service, can aggregate information from the three core product 
services and compose a view of product information together with reviews 
and recommendations of a product. Dynamic Routing and Load Balancer Ribbon 
are used by this service to lookup services at runtime.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see Product Composite service

## Edge Server

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run"). It will start up on port
8765.

Edge Server Zuul is our gatekeeper to the outside world, not allowing 
any unauthorized external requests pass through. Zuul also provides a 
well known entry point to the microservices in the system landscape. 
Zuul uses Ribbon to lookup available services and routes the external 
request to an appropriate service instance.

Start to call the composite service through the edge server, The edge 
server is found on the port 8765  and as we have seen above we can use 
the path /api/product-composite/** to reach the product-composite 
service through our edge server.

## Turbine Service

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

Turbine can, based on information in Eureka, provide the dashboard 
with information from all circuit breakers in a system landscape.

The Turbine dashboard will be accessible at
http://localhost:8989/turbine.stream?cluster=default

## Dashboard Service

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

Dashboard Service will be behind API Gateway.

http://localhost:8765/dashboard

On the home page is a form where you can enter the URL for an event stream 
to monitor, for example The Turbine dashboard will be accessible at:

http://localhost:8989/turbine.stream?cluster=default

## Authentication and Authorization Server

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run"). It will start up on port
9999 and serve the Auth API from "/uaa/oauth".

http://localhost:9999/uaa/oauth/signup
http://localhost:9999/uaa/oauth/approve-user/{username}

AUTHORIZATION CODE GRANT:
http://localhost:9999/uaa/oauth/authorize?client_id=saba-product-api-service&redirect_uri=http://localhost:8765/api/product/login&scope=read&response_type=code&state=54321

http://localhost:9999/uaa/oauth/token?client_id=saba-product-api-service&grant_type=authorization_code&redirect_uri=http://localhost:8765/api/product/login&scope=read&code=JETmEA&state=54321

IMPLICIT GRANT:
http://localhost:9999/uaa/oauth/authorize?response_type=token&client_id=saba-product-api-service&redirect_uri=http://localhost:8765/api/product/login&scope=read&state=54321

RESOURCE OWNER PASSWORD CREDENTIALS GRANT:
http://localhost:9999/uaa/oauth/token?client_id=saba-product-api-service&grant_type=password&scope=read&username=admin&password=admin123

CLIENT CREDENTIALS GRANT:
http://localhost:9999/uaa/oauth/token?grant_type=client_credentials&scope=read

You can check token from:
http://localhost:9999/uaa/oauth/check_token?token=(your token)

Users: (admin,admin123,ROLE_ADMIN),(user,user123,ROLE_USER)
Client: (saba-product-api-service,secret123)