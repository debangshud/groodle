# netflix-eureka-naming-server

## How to build?

    mvn clean package dockerfile:build
    
## How to run?

    docker run -d --name netflix-eureka-naming-server -p 8761:8761 netflix-eureka-naming-server:0.0.1-SNAPSHOT    

## Run as a service in docker swarm
    
    docker service create \
        --name netflix-eureka-naming-server \
        -p 8761:8761 \
        -e CLOUD_CONFIG_HOST=45.55.50.81 \
        -e CLOUD_CONFIG_PORT=8888 \
        -e PROFILE=dev \
        debangshud/netflix-eureka-naming-server 


    docker service create \
        --name netflix-eureka-naming-server \
        -p 8761:8761 \
        -e PROFILE=dev \
        debangshud/netflix-eureka-naming-server

             
## List services running in docker swarm

    docker service ls
    
## Show details of netflix-eureka-naming-server docker service
    
    docker service ps netflix-eureka-naming-server     
    