#!/bin/bash

printmessage() {
	msg="$1"
	echo "*******************"
	echo $msg
	echo "*******************"
}

printmessage "Getting microservice repositories..." 

if [ -d "saba-microservices" ] 
then
	pushd saba-microservices &>> /tmp/null
	git pull origin master &>> /tmp/null
	res=$?
else
	git clone https://github.com/babaei503/saba-microservices.git &>> /tmp/null
	res=$?
	pushd saba-microservices &>> /tmp/null
fi
if [[ res -eq 0 ]]
then
	echo "--> Getting microservice repositories - Success"
else
	echo "--> Getting microservice repositories - Fail"
	exit 1
fi

build() {

	microservice="$1"	
	
	printmessage "Building $microservice..."
	
	pushd $microservice &>> /tmp/null 
	
	mvn package -Dmaven.test.skip=true &>> /tmp/null
	
	if [[ $? -eq 0 ]]
	then
		echo "--> Building $microservice - Success"
	else
		echo "--> Building $microservice - Fail"
		popd &>> /tmp/null
		exit 1
	fi
	
	popd &>> /tmp/null

	return 0
}

#Start of building microservices
build "microservices/support/discovery-server"

build "microservices/support/config-server"

build "microservices/support/edge-server"

build "microservices/support/auth-server"

build "microservices/core/product-service"

build "microservices/core/product-review-service"

build "microservices/core/product-recommendation-service"

build "microservices/composite/product-composite-service"

build "microservices/api/product-api-service"

build "microservices/support/turbine-service"

build "microservices/support/dashboard-service"
#End of building microservices

printmessage "End of building microservices";

printmessage "Running microservices on Docker";

pushd saba-microservices &>> /tmp/null
COMPOSE_HTTP_TIMEOUT=200 docker-compose up -d
popd &>> /tmp/null

printmessage "--> Running microservices on Docker - Success";

popd &>> /tmp/null

exit 0
