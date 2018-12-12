# sample-spring-boot-rabbitmq

## How to build?

Clone the project using git url

    git clone https://github.com/debangshud/sample-spring-boot-rabbitmq.git
    cd sample-spring-boot-rabbitmq
    mvn clean package dockerfile:build
    

## How to run?

Run a **rabbitmq** docker container

```docker
  docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 -p 5671:5671 rabbitmq:3.7.3-management
  docker run -d --name sample-spring-boot-rabbitmq --link rabbitmq:rabbitmq -p 10001:10001 -e RABBITMQ_HOST=rabbitmq sample-spring-boot-rabbitmq:0.0.1-SNAPSHOT 
```    