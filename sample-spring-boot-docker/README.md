# SPRING BOOT DOCKER
A sample project for creating spring boot docker image 
## BUILD
cd to a directory (e.g. opt/projects)

    opt/projects>git clone https://github.com/debangshud/sample-spring-boot-docker.git
    opt/projects>cd sample-spring-boot-docker
    opt/projects/sample-spring-boot-docker>mvn clean package dockerfile:build

## Run 
    opt/projects/sample-spring-boot-docker> docker images

Above command will give you the list of images installed

    docker run -d -p 10002:10002 sample-spring-boot-docker:0.0.1-SNAPSHOT
    
## Test    
    
Open a browser window and hit following url:
    
    http://localhost:10002/

You should see a message:

    Hello World, I am Acacia

## Run as a service in docker swarm
    docker service create \
    	--replicas 1 \
    	--name sample-spring-boot-docker \
    	-p 10002:10002 \
    	-e CLOUD_CONFIG_HOST=45.55.50.81 \
        -e CLOUD_CONFIG_PORT=8888 \
        -e PROFILE=dev \
    	debangshud/sample-spring-boot-docker
    	
This will create 5 docker instances    	