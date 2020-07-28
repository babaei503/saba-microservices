:: Start microservices
:: Discovery Server
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer1
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer2
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer3
:: Config Server
start /D  microservices\support\config-server	mvn spring-boot:run