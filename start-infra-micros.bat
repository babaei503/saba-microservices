:: Start microservices
:: Discovery Server
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer1
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer2
start /D  microservices\support\discovery-server	mvn spring-boot:run -Dspring-boot.run.profiles=peer3
:: Edge Server
start /D  microservices\support\edge-server	mvn spring-boot:run
:: Monitor Dashboard
start /D  microservices\support\dashboard-service	mvn spring-boot:run
:: Turbine Service
start /D  microservices\support\turbine-service	mvn spring-boot:run
:: Auth Server
start /D  microservices\support\auth-server	mvn spring-boot:run