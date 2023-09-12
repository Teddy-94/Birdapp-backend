# Birdapp microservices monorepo

This is the Birdapp microservices backend, build in Spring Boot. 

## TODO
* These services will eventually be dockerized to enable easy deployment and running.
* Auth service
* Eventually it would be cool to use a graph db for the users. 
* Target is to get all of the birdapp deployed somewhere. possibly aws

## Services

### Chirps
The chirps microservice handles all the chirps on the birdapp (think posts). It connects to a mysql db and has full crud funcitonality (TODO)
runs on port 8090

### Users
The users microservice handles all users and user related funcitonality. This is currently stored in the same sql db as the chirps, but will be migrated to a graph db to enable all the cool stuff.
runs on port 8070

### Gateway
The microservices are exposed via the gateway. It runs on port 8222 and forwards the requests to the related service.

### Discovery
The birdapp backend has a discovery service in spring eureka, which runs on port 8761.