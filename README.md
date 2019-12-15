# Spring boot demo app
The provides end points to add, list, update and soft delete products.

In order to run please make sure you have the following installed:
* JDK 8+
* Docker CE 19.03.5

In order to start the application run the script ```run.sh```. 
The script will `run` the tests, `package` the application, create a docker 
container and run the `docker-compose up -d` command. This will launch a postgres
docker instance as well as the application. Once up and running the application can 
be accessed http://localhost:9000/swagger-ui.html. 