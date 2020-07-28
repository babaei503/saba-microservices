# Config Server

This service will act as a configuration server and we will expose 
its services through the 8888 port.

We use this server to centralize management of all the configuration files.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

The configuration files are stored locally in a Git repo during development, 
but are normally hosted on a remote Git server.

You can find config Git repo on saba-microservices-config repository.

You can call the end point from:

http://localhost:8888/microservice-name/profile

for example:

http://localhost:8888/saba-product-api-service/default

Use Basic Authentication (admin, admin) to call the end point.

You should configure bootstrap.yml of the other microservices to use the 
configuration server. Microservices can lookup a config server using the 
discovery server or bind directly to the config server url.