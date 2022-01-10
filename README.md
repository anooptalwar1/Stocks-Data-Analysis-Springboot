# Stocks-Data-Analysis-Springboot

## Local Development (FOR PC)

1. Start PostgreSQL instance

`docker run --name portal_postgres -p 5432:5432 -e POSTGRES_HOST_AUTH_METHOD=trust -d postgres`

2. Migration script get automatically executed using Flyway and is available in db.migrations in resources

3. Application requires authenticated access.
    - Two default users(admin, viewer) already added through migrations
    - Create your user in users table

4. After compiling with Maven and running the applications access the API documentation using URL's
    - http://localhost:8080/api-docs/ ---- Json Format
    - http://localhost:8080/api-docs.yaml  ---- Yaml format
    - http://localhost:8080/swagger-ui.html ---- Swagger UI

5. API's can also be tested using postman collection
    - "Pyconiq.postman_collection.json" available in root path of git repo

6. Retrieve Token using API
    - http://localhost:8080/api/reporting/auth with {"userName": "admin",
      "password": "admin"} sent in request body as JSON.

7. Maven Build jar
   - mvn clean package
   - cd target
   StocksDataAnalysis.jar will be available after above command execution

8. Docker builds
   - docker build -t stocks_data_analysis .
   
9. Running in docker
    - docker network create stocks
    - docker run --name portal_postgres -p 5432:5432 -e POSTGRES_HOST_AUTH_METHOD=trust -d postgres
    - docker network connect stocks portal_postgres
    - docker run -it -e HOST_IP=localhost -p 8080:8080 -d stocks_data_analysis --network=stocks
