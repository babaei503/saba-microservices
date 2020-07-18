pushd microservices\support\discovery-server &                call mvn compile & popd
pushd microservices\core\product-service &                call mvn compile & popd
pushd microservices\core\product-review-service &                call mvn compile & popd
pushd microservices\core\product-recommendation-service &                call mvn compile & popd
pushd microservices\composite\product-composite-service &                call mvn compile & popd
pushd microservices\support\edge-server &                call mvn compile & popd
pushd microservices\support\turbine-service &                call mvn compile & popd
pushd microservices\support\dashboard-service &                call mvn compile & popd
pushd microservices\support\auth-server &                call mvn compile & popd
pushd microservices\api\product-api-service &                call mvn compile & popd
@echo off
pause