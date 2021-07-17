cd microservices/support/discovery-server; echo "*******************"; echo "Building discovery-server..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/support/config-server; echo "*******************"; echo "Building config-server..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/support/edge-server; echo "*******************"; echo "Building edge-server..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/support/auth-server; echo "*******************"; echo "Building auth-server..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/core/product-service; echo "*******************"; echo "Building product-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/core/product-review-service;echo "*******************"; echo "Building product-review-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/core/product-recommendation-service; echo "*******************"; echo "Building product-recommendation-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/composite/product-composite-service; echo "*******************"; echo "Building product-composite-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/api/product-api-service; echo "*******************"; echo "Building product-api-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/support/turbine-service; echo "*******************"; echo "Building turbine-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
cd microservices/support/dashboard-service; echo "*******************"; echo "Building dashboard-service..."; echo "*******************"; mvn package -Dmaven.test.skip=true; cd -
echo "*******************";
echo "*****************";
echo "***************";
echo "*************";
echo "End of building microservices";
echo "*************";
echo "***************";
echo "*****************";
echo "*******************";
