# =========================================================
# 1. Create portainer service
# =========================================================
docker service create \
--name portainer \
--publish 9000:9000 \
--constraint 'node.role == manager' \
--mount type=bind,src=//var/run/docker.sock,dst=/var/run/docker.sock \
portainer/portainer \
-H unix:///var/run/docker.sock
# =========================================================
# 2. Create overlay network
# =========================================================
docker network create --driver overlay acacia-service-network
# =========================================================
# 3. Create cloud config service
# =========================================================
docker service create \
--name spring-cloud-config-server \
-p 8888:8888 \
--network acacia-service-network \
debangshud/spring-cloud-config-server
# =========================================================
# 4. Create naming service
# =========================================================
docker service create \
--name netflix-eureka-naming-server \
-p 8761:8761 \
-e CLOUD_CONFIG_HOST=spring-cloud-config-server \
-e CLOUD_CONFIG_PORT=8888 \
--network acacia-service-network \
-e PROFILE=dev \
debangshud/netflix-eureka-naming-server
#========================================================
docker service create \
--name sample-spring-boot-docker \
-p 10002:10002 \
-e CLOUD_CONFIG_HOST=spring-cloud-config-server \
-e CLOUD_CONFIG_PORT=8888 \
--network acacia-service-network \
-e PROFILE=dev \
debangshud/sample-spring-boot-docker
#========================================================
docker service create \
--name mysqldb \
-p 3306:3306 \
-e MYSQL_DATABASE=mysqldb \
-e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
mysql