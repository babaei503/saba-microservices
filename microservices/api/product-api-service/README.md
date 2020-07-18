# Product API Service

This service will act as the external API (a Resource Server in OAuth 
terminology) and we will expose its services through the edge server.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see our service
http://localhost:8761

You can call the end point from:

http://localhost:8765/api/product/1

You must send auth-server bearer token in the Authorization header. 
