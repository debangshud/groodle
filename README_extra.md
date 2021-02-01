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