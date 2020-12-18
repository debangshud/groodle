# Spring Boot Micro Services

## Setup Development Environment
### Setup Minishift
#### Setting Up Virtualization Environment
##### MacOS
```shell
brew install hyperkit
sudo chown root:wheel /usr/local/bin/hyperkit
sudo chmod u+s /usr/local/bin/hyperkit
brew install docker-machine-driver-hyperkit
sudo chown root:wheel /usr/local/bin/docker-machine-driver-hyperkit
sudo chmod u+s,+x /usr/local/bin/docker-machine-driver-hyperkit
```
### Start Minishift
```shell
minishift start
```
The server is accessible via web console at:
https://192.168.99.100:8443/console

Username: developer
Password: developer
```shell
# Username: developer
# Password: developer    

oc login
oc new-project groodle
oc project groodle
oc policy add-role-to-user view system:serviceaccount:groodle:default
```
### Minishift Commands

```shell
minishift stop
```
```shell
minishift status
```
```shell
minishift console
```

## Services
### Postgresql
```shell script
oc port-forward <postgresql pod name> 5432:5432
```

### 1. Distributed Configuration Service (config-service)
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