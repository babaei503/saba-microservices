pushd microservices\support\discovery-server &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\support\config-server &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\support\edge-server &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\support\auth-server &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\core\product-service &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\core\product-review-service &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\core\product-recommendation-service &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\composite\product-composite-service &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\api\product-api-service &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\support\turbine-service &                call mvn package -Dmaven.test.skip=true & popd
pushd microservices\support\dashboard-service &                call mvn package -Dmaven.test.skip=true & popd
docker-compose up --build
@echo off
pause