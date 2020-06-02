# Product Composite Service

This service, can aggregate information from the three core product 
services and compose a view of product information together with reviews 
and recommendations of a product.Dynamic Routing and Load Balancer Ribbon 
are used by this service to lookup services at runtime.

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run").

In the service discovery web app we should be able to see our service
http://localhost:8761


