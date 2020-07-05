# Authentication and Authorization Server

Run this project as a Spring Boot app (e.g. import into IDE and run
main method, or use "mvn spring-boot:run"). It will start up on port
9999 and serve the Auth API from "/uaa/oauth".

http://localhost:9999/uaa/oauth/signup
http://localhost:9999/uaa/oauth/approve-user/{username}

AUTHORIZATION CODE GRANT:
http://localhost:9999/uaa/oauth/authorize?client_id=saba-product-api-service&redirect_uri=http://localhost:8765/api/product/login&scope=read&response_type=code&state=54321

http://localhost:9999/uaa/oauth/token?client_id=saba-product-api-service&grant_type=authorization_code&redirect_uri=http://localhost:8765/api/product/login&scope=read&code=JETmEA&state=54321

IMPLICIT GRANT:
http://localhost:9999/uaa/oauth/authorize?response_type=token&client_id=saba-product-api-service&redirect_uri=http://localhost:8765/api/product/login&scope=read&state=54321

RESOURCE OWNER PASSWORD CREDENTIALS GRANT:
http://localhost:9999/uaa/oauth/token?client_id=saba-product-api-service&grant_type=password&scope=read&username=admin&password=admin123

CLIENT CREDENTIALS GRANT:
http://localhost:9999/uaa/oauth/token?grant_type=client_credentials&scope=read

You can check token from:
http://localhost:9999/uaa/oauth/check_token?token=(your token)

Users: (admin,admin123,ROLE_ADMIN),(user,user123,ROLE_USER)
Client: (saba-product-api-service,secret123)