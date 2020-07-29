:: Start microservices
:: Product Service
start /D  microservices\core\product-service	mvn spring-boot:run
:: start /D  microservices\core\product-service	mvn spring-boot:run
:: Product Review Service
start /D  microservices\core\product-review-service	mvn spring-boot:run
:: start /D  microservices\core\product-review-service	mvn spring-boot:run
:: Product Recommendation Service
start /D  microservices\core\product-recommendation-service	mvn spring-boot:run
:: start /D  microservices\core\product-recommendation-service	mvn spring-boot:run
:: Product Composite Service
start /D  microservices\composite\product-composite-service	mvn spring-boot:run
:: start /D  microservices\composite\product-composite-service	mvn spring-boot:run
:: Product API Service
start /D  microservices\api\product-api-service	mvn spring-boot:run
:: start /D  microservices\api\product-api-service	mvn spring-boot:run