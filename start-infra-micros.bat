:: Start microservices
:: Edge Server
start /D  microservices\support\edge-server	mvn spring-boot:run
:: Auth Server
start /D  microservices\support\auth-server	mvn spring-boot:run
:: start /D  microservices\support\auth-server	mvn spring-boot:run
:: Monitor Dashboard
start /D  microservices\support\dashboard-service	mvn spring-boot:run
:: Turbine Service
start /D  microservices\support\turbine-service	mvn spring-boot:run
:: Tracing Server
start java -jar  microservices\support\tracing-server\zipkin-server-2.21.5-exec.jar