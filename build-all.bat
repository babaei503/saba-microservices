pushd microservices\support\discovery-server &                call mvn compile & popd
pushd microservices\core\product-service &                call mvn compile & popd
pushd microservices\core\product-review-service &                call mvn compile & popd
pushd microservices\core\product-recommendation-service &                call mvn compile & popd
@echo off
pause