# spring-cloud-config-server

## Run
Run eureka naming server in a docker container
    
    docker run -d --name netflix-eureka-naming-server -p 8761:8761 netflix-eureka-naming-server:0.0.1-SNAPSHOT 

Run rabbitmq in a docker container

     docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 -p 5671:5671 rabbitmq:3.7.3-management   
     
Run spring-cloud-config-server as a service in docker swarm
    
    docker service create \
    --name spring-cloud-config-server \
    -p 8888:8888 \
    debangshud/spring-cloud-config-server      
    
        
        
