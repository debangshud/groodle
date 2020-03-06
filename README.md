# Spring Boot Micro Services

## Installations

**Oracle Express**
----------
**Credentials**

|Username|Password  |
|--|--|
|SYSTEM  | admin |
|SYS  | admin |


**Ports**

|Service|Port  |
|--|--|
| DB | 1521 |
| APEX | 8080 |

## Services

### Distributed Configuration Service (config-service)

### OAuth Service (oauth-service)

#### FAQ

##### How to build?

```shell script
cd oauth-service
mvn clean package
```
OR
```shell script
cd oauth-service
mvn clean install
```

##### How to run locally?
```shell script
cd oauth-service
mvn spring-boot:run
```

##### How to deploy in openshift (minishift)?

```shell script
oc login
cd oauth-service
mvn clean fabric8:deploy
```

##### How to access H2 console?
http://localhost:8080/h2-console

##### How to use 'password' grant type and get an access token for a given client id, client secret, username and password? 

```shell script
curl --location --request POST 'http://localhost:8080/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'username=user' \
--data-urlencode 'password=user'
```
##### How to use 'client_credentials' grant type and get an access token for a given client id, client secret, username and password 

```shell script
curl --location --request POST 'http://localhost:8080/oauth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Authorization: Basic Z3Jvb2RsZTpncm9vZGxlc2VjcmV0' \
--data-urlencode 'grant_type=client_credentials'
```
### API Gateway Service (api-gateway-service)

### Customer Service (customer-service)

### Employee Service (customer-service)
