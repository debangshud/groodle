Spring Boot/Kubernetes Micro Services
-------------------------------------

**Table of Contents**
- [Setup](#setup)
    - [Minishift](#minishift)
    - [CRC](#CRC)
- [Services](#services)
    - [Postgresql](#postgresql)
    - [MongoDB](#mongodb)
    - [Configuration Service](#configuration-service)
    - [OAuth Service](#oauth-service)
    - [API Gateway Service](#api-gateway-service)
    - [Customer Service](#customer-service)
    - [Employee Service](#employee-service)
    - [Product Service](#product-service)
    - [Reference Service](#reference-service)
## Setup
### Minishift
Setting up minishift in MacOS
```shell
brew install hyperkit
sudo chown root:wheel /usr/local/bin/hyperkit
sudo chmod u+s /usr/local/bin/hyperkit
brew install docker-machine-driver-hyperkit
sudo chown root:wheel /usr/local/bin/docker-machine-driver-hyperkit
sudo chmod u+s,+x /usr/local/bin/docker-machine-driver-hyperkit
minishift start
#The server is accessible via web console at:
#https://192.168.99.100:8443/console
#
#Username: developer
#Password: developer
## Username: developer
## Password: developer    
$ minishift status
$ minishift console
$ oc login
$ oc new-project groodle
$ oc project groodle
$ oc policy add-role-to-user view system:serviceaccount:groodle:default
$ minishift stop
```
### CRC
Download and install CodeReady Container for MacOS<br>
Setup and Start CodeReady Container
```shell
$ crc setup
$ crc start
$ crc console --credentials
$ oc login -u <username> -p <password> https://api.crc.testing:6443
$ oc new-project groodle
$ oc project groodle
$ oc policy add-role-to-user view system:serviceaccount:groodle:default
```
Stop and Delete CodeReady Container
Delete 
```shell
$ crc stop
$ crc delete
```
## Services
### Redis
```shell script
$ oc new-app registry.redhat.io/rhscl/redis-6-rhel7 --name=redis
$ oc port-forward redis-6-rhel7-5547dbb659-n8h7b 6379:6379
```
### Postgresql
```shell script
$ oc new-app \
    -e POSTGRESQL_USER=developer \
    -e POSTGRESQL_PASSWORD=developer \
    -e POSTGRESQL_DATABASE=sampledb \
    registry.access.redhat.com/rhscl/postgresql-95-rhel7 \
    --name=postgresql
$ oc port-forward <postgresql pod name> 5432:5432
```
### MongoDB
```shell script
$ oc new-app \
    -e MONGODB_USER=developer \
    -e MONGODB_PASSWORD=developer \
    -e MONGODB_DATABASE=sampledb \
    -e MONGODB_ADMIN_PASSWORD=admin \
    registry.access.redhat.com/rhscl/mongodb-26-rhel7 \
    --name=mongodb
$ oc port-forward <mongodb pod name> 5432:5432
```
### Configuration Service
Build & Deploy
```shell
$ cd config-service
$ mvn oc:deploy
$ curl --location --request GET '<base_url>/customer-service/default'
```
### OAuth Service
Build & Deploy
```shell script
$ cd oauth-service
$ mvn oc:deploy

# Use 'password' grant type and get an access token for a given client id, client secret, username and password
$ curl --location --request POST '<base_url>/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=user' \
--data-urlencode 'password=user'

# Use 'client_credentials' grant type and get an access token for a given client id, client secret, username and password 
$ curl --location --request POST '<base_url>/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=client_credentials'
```
H2 console: http://localhost:8080/h2-console
### API Gateway Service
Build & Deploy
```shell script
$ cd api-gateway-service
$ mvn oc:deploy 
$ curl --location --request GET '<base_url>/.well-known/jwks.json'
```
### Customer Service
Build & Deploy
```shell script
$ cd customer-service
$ mvn oc:deploy
$ curl --location --request GET '<base_url>/1'
```
### Employee Service
Build & Deploy
```shell script
$ cd employee-service
$ mvn oc:deploy
$ curl --location --request GET '<base_url>/1'
```
### Product Service
Build & Deploy
```shell script
$ cd product-service
$ mvn oc:build oc:resource oc:deploy 
$ curl --location --request GET '<base_url>/1'
```
### Reference Service
Build & Deploy
```shell script
$ cd reference-service
$ mvn oc:build oc:resource oc:deploy 
$ curl --location --request GET '<base_url>/1'
```