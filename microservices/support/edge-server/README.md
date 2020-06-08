# Edge Server

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