# Spring Boot Microservices

You can run build-all.bat in windows to build all project.
you can run start-base-infra-micros.bat and then start-infra-micros.bat 
in windows to run all infrastructure microservices.
you can run start-busns-micros.bat in windows to run all business microservices.

To be able to create docker images and run the microservices in a 
Docker environment you can run docker-build-run.bat

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

for Docker environment:
http://turbine-service:8989/turbine.stream?cluster=default

## Authentication and Authorization Server

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run"). It will start and serve 
the Auth API from "/uaa/oauth".

http://localhost:8765/uaa/oauth/signup
http://localhost:8765/uaa/oauth/approve-user/{username}

AUTHORIZATION CODE GRANT:
http://localhost:8765/uaa/oauth/authorize?client_id=saba-product-api-service&redirect_uri=http://localhost:8765/api/product/login&scope=read&response_type=code&state=54321

http://localhost:8765/uaa/oauth/token?client_id=saba-product-api-service&grant_type=authorization_code&redirect_uri=http://localhost:8765/api/product/login&scope=read&code=JETmEA&state=54321

IMPLICIT GRANT:
http://localhost:8765/uaa/oauth/authorize?response_type=token&client_id=saba-product-api-service&redirect_uri=http://localhost:8765/api/product/login&scope=read&state=54321

RESOURCE OWNER PASSWORD CREDENTIALS GRANT:
http://localhost:8765/uaa/oauth/token?client_id=saba-product-api-service&grant_type=password&scope=read&username=admin&password=admin123

CLIENT CREDENTIALS GRANT:
http://localhost:8765/uaa/oauth/token?grant_type=client_credentials&scope=read

You can check token from:
http://localhost:8765/uaa/oauth/check_token?token=(your token)

Users: (admin,admin123,ROLE_ADMIN),(user,user123,ROLE_USER)
Client: (saba-product-api-service,secret123)

## Product API Service

This service will act as the external API (a Resource Server in OAuth 
terminology) and we will expose its services through the edge server.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see our service
http://localhost:8761

Start to call the product API service through the edge server, The edge 
server is found on the port 8765 and we can use the path 
http://localhost:8765/api/product/** to reach the product-api 
service through our edge server.

You must send auth-server bearer token in the Authorization header.

## Config Server

This service will act as a configuration server and we will expose 
its services through the 8888 port.

We use this server to centralize management of all the configuration files.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

The configuration files are stored locally in a Git repo during development, 
but are normally hosted on a remote Git server.

You can find config Git repo on saba-microservices-config repository.

You can call the end point to get configuration file from:

http://localhost:8888/microservice-name/profile

for example:

http://localhost:8888/saba-product-api-service/default

The config server provides two end point for encrypting and decrypting 
sensitive properties: 

http://localhost:8888/encrypt 

and 

http://localhost:8888/decrypt

Use Basic Authentication (admin, admin) to call the end point.

To be able to be notified on changes of the configuration in a Git repo on a 
Git server the config server supports webhook based push notifications. 
A webhook can be registered in the Git repo using a URL like:

http://dns-name-for-the-config-server:8888/monitor

@RefreshScope annotation, instruct Spring to reinject all fields and setter 
methods that are annotated with the @Value if their configuration property 
is changed. 

You should install RabbitMQ server to use push notifications.

You should configure bootstrap.yml of the other microservices to use the 
configuration server. Microservices can lookup a config server using the 
discovery server or bind directly to the config server url.

## Tracing Server

Zipkin is a distributed tracing system. It helps gather timing data needed 
to troubleshoot latency problems in service architectures. Features include 
both the collection and lookup of this data.

The Zipkin server runs as a standalone jar.
start java -jar  microservices\support\tracing-server\zipkin-server-2.21.5-exec.jar

The Zipkin UI also presents a Dependency diagram showing how many traced requests 
went through each application.

You can open the Zipkin Web UI in your web browser: 

http://localhost:9411/zipkin/

To enable our microservices to send trace events to the Zipkin server we have added 
the spring-cloud-starter-sleuth dependency.

You should install RabbitMQ server. The trace events are sent to the Zipkin server 
using RabbitMQ.

## Prometheus

Just for Docker environment!

Prometheus is used for event monitoring and alerting. It records real-time metrics 
in a time series database built using a HTTP pull model, with flexible queries and 
real-time alerting.

You can open the Prometheus Web UI in your web browser: 

http://localhost:9090/graph

for example enter process_cpu_usage and click Execute button.

## Grafana

Just for Docker environment!

Grafana is an analytics and interactive visualization web application. It provides 
charts, graphs, and alerts for the web when connected to supported data sources. 
Grafana supports querying Prometheus.

You can open the Grafana Web UI in your web browser: 

http://localhost:3000/

Create a "Prometheus" datasource and set the Prometheus server URL 
to http://localhost:9090

Import 1598 (Zipkin / Prometheus) dashboard or create a new one.

Enjoy! 

