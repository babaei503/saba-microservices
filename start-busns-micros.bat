:: Start microservices
:: Product Service
start /D  microservices\core\product-service	mvn spring-boot:run
start /D  microservices\core\product-service	mvn spring-boot:run
start /D  microservices\core\product-service	mvn spring-boot:run -Dspring-boot.run.profiles=fallback
:: Product Review Service
start /D  microservices\core\product-review-service	mvn spring-boot:run
start /D  microservices\core\product-review-service	mvn spring-boot:run
start /D  microservices\core\product-review-service	mvn spring-boot:run -Dspring-boot.run.profiles=fallback
:: Product Recommendation Service
start /D  microservices\core\product-recommendation-service	mvn spring-boot:run
start /D  microservices\core\product-recommendation-service	mvn spring-boot:run
start /D  microservices\core\product-recommendation-service	mvn spring-boot:run -Dspring-boot.run.profiles=fallback
:: Product Composite Service
start /D  microservices\composite\product-composite-service	mvn spring-boot:run
start /D  microservices\composite\product-composite-service	mvn spring-boot:run