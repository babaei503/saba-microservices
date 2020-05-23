start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer1
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer2
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer3