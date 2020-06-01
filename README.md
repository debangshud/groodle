# Spring Boot Micro Services

## Setup Development Environment
### Create MongoDB Development Environment

Creae 3 Virtual Machines using Centos-7

```markdown
Guest OS: Centos 7
RAM: 1GB
Storage: 20GB
Network: Bridge Adapter
```

* Install MongoDB

```shell script
ssh root@host_ip
yum update -y
```
* Configure the package management system (yum).Create a /etc/yum.repos.d/mongodb-org-4.2.repo file so that you can install MongoDB directly using yum:
```shell script
[mongodb-org-4.2]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/redhat/$releasever/mongodb-org/4.2/x86_64/
gpgcheck=1
enabled=1
gpgkey=https://www.mongodb.org/static/pgp/server-4.2.asc
```
* Install the MongoDB packages.
```shell script
sudo yum install -y mongodb-org
```

* Open firewall
```shell script
sudo firewall-cmd --permanent --add-port=27017/tcp
sudo firewall-cmd --reload
```
* Configurae Replica Set

```renderscript

mongo --host 192.168.86.35

var configuration = {
    _id: 'dev',
    members: [
        {_id:0, host: '192.168.86.35:27017'},
        {_id:1, host: '192.168.86.29:27017', slaveDelay: 20, priority: 0},
        {_id:2, host: '192.168.86.37:27017'}
    ]
}

rs.initiate(configuration)
```

* Install HTTPD
```shell script
sudo yum update httpd
sudo yum install httpd
sudo firewall-cmd --permanent --add-service=http
sudo firewall-cmd --permanent --add-service=https
sudo firewall-cmd --reload
sudo systemctl start httpd
```
#### Details

|Server |IP            |
|-------|--------------|
|Mongo 1|192.168.86.35 |
|Mongo 2|192.168.86.29 |
|Mongo 3|192.168.86.37 |

```shell script
ssh root@192.168.86.35 #root password is root
sudo systemctl start mongod
sudo systemctl status mongod
```
```shell script
ssh root@192.168.86.29 #root password is root
sudo systemctl start mongod
sudo systemctl status mongod
```
```shell script
ssh root@192.168.86.37 #root password is root
sudo systemctl start mongod
sudo systemctl status mongod
```

### Minishift

### Oracle

#### Installation

##### Oracle Virtual Box
###### Connection Details
|Host|Service|Port  |
|----|-------|------|
|localhost|DB|1521|
|localhost|APEX|8080|

Note: Oracle VM is natted and port forwarded. Vm can be reached using "localhost"

###### Credentials
|Database|Username|Password|
|--------|--------|--------|
|orcl|SYSTEM|oracle|

##### Container

##### Credentials
|Username|Password|
|--------|--------|
|SYSTEM|admin|
|SYS|admin|
#### Connection Details
|Service|Port|
|-------|----|
|DB|1521|
|APEX|8080|

## Services
### Minishift
```shell script
minishift stop
minishift status
minishift start --vm-driver virtualbox
```
The server is accessible via web console at:
    https://192.168.99.100:8443/console

Username: developer
Password: developer    
```
# Username: developer
# Password: developer    

oc login
oc policy add-role-to-user view system:serviceaccount:groodle:default
oc new-project groodle
oc project groodle
```

### Postgresql

```shell script
oc port-forward postgresql-1-j8bdd 5432
```

### 1. Distributed Configuration Service (config-service)
#### Build & Deploy
```shell script
# login to openshift using username:developer & password:developer 
cd config-service
oc apply -f openshift/configmap.yml
mvn clean fabric8:deploy
curl --location --request GET 'http://config-service-groodle.192.168.64.2.nip.io/customer-service/default'
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