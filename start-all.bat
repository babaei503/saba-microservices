:: Start microservices
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer1
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer2
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer3
start /D  microservices\core\product-service	mvn spring-boot:run
start /D  microservices\core\product-review-service	mvn spring-boot:run
start /D  microservices\core\product-recommendation-service	mvn spring-boot:run
start /D  microservices\composite\product-composite-service	mvn spring-boot:run