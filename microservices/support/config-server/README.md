# Config Server

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