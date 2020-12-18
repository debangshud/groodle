Spring Boot/Kubernetes Micro Services
-------------------------------------

**Table of Contents**
- [Setup](#setup)
    - [Minishift](#minishift)
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
minishift status
minishift console
oc login
oc new-project groodle
oc project groodle
oc policy add-role-to-user view system:serviceaccount:groodle:default
minishift stop
```
## Services
### Postgresql
```shell script
oc port-forward <postgresql pod name> 5432:5432
```
### MongoDB
```shell script
oc port-forward <mongodb pod name> 5432:5432
```

### Configuration Service
Build & Deploy
```shell
cd config-service
oc login #using username:developer & password:developer 
oc apply -f openshift/configmap.yml
mvn clean fabric8:deploy
curl --location --request GET '<base_url>/customer-service/default'
```
### OAuth Service
Build & Deploy
```shell script
cd oauth-service
mvn clean fabric8:deploy
```
H2 console: http://localhost:8080/h2-console
Use 'password' grant type and get an access token for a given client id, client secret, username and password
```shell script
curl --location --request POST '<base_url>/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=user' \
--data-urlencode 'password=user'
```
Use 'client_credentials' grant type and get an access token for a given client id, client secret, username and password 
```shell script
curl --location --request POST '<base_url>/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=client_credentials'
```
### API Gateway Service
Build & Deploy
```shell script
cd api-gateway-service
mvn clean fabric8:deploy 
curl --location --request GET '<base_url>/.well-known/jwks.json'
```
### Customer Service
Build & Deploy
```shell script
cd customer-service
mvn clean fabric8:deploy 
curl --location --request GET '<base_url>/1'
```

### Employee Service
Build & Deploy
```shell script
cd employee-service
mvn clean fabric8:deploy 
curl --location --request GET '<base_url>/1'
```
### Product Service
Build & Deploy
```shell script
cd product-service
mvn clean fabric8:deploy 
curl --location --request GET '<base_url>/1'
```
### Reference Service
Build & Deploy
```shell script
cd reference-service
mvn clean fabric8:deploy 
curl --location --request GET '<base_url>/1'
```