Spring Boot/Kubernetes Micro Services
-------------------------------------

**Table of Contents**
- [Setup](#setup)
    - [Minishift](#minishift)
- [Services](#services)
    - [Postgresql](#postgresql)
    - [MongoDB](#mongodb)
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

### Distributed Configuration Service (config-service)
#### Build & Deploy
```shell
# login to openshift using username:developer & password:developer 
cd config-service
oc apply -f openshift/configmap.yml
mvn clean fabric8:deploy
curl --location --request GET 'http://config-service-groodle.<IP>.nip.io/customer-service/default'
```
### 2. OAuth Service (oauth-service)
#### Build & Deploy
```shell script
cd oauth-service
mvn clean fabric8:deploy
```
#### Access H2 console
http://localhost:8080/h2-console
#### Use 'password' grant type and get an access token for a given client id, client secret, username and password
```shell script
curl --location --request POST 'http://oauth-service-groodle.192.168.64.2.nip.io/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=user' \
--data-urlencode 'password=user'
```
#### Use 'client_credentials' grant type and get an access token for a given client id, client secret, username and password 
```shell script
curl --location --request POST 'http://oauth-service-groodle.192.168.64.2.nip.io/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=client_credentials'
```
### 3. API Gateway Service (api-gateway-service)
#### Build & Deploy
```shell script
cd api-gateway-service
mvn clean fabric8:deploy 
curl --location --request GET http://oauth-service-groodle.192.168.64.2.nip.io/.well-known/jwks.json
```
### 4. Customer Service (customer-service)
#### Build & Deploy
```shell script
cd customer-service
mvn clean fabric8:deploy 
curl --location --request GET 'http://customer-service-groodle.192.168.64.2.nip.io/1'
```

### 5. Employee Service (customer-service)
#### Build & Deploy
```shell script
cd employee-service
mvn clean fabric8:deploy 
curl --location --request GET 'http://employee-service-groodle.192.168.64.2.nip.io/1'
```
