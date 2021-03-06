## Jenkins installation on EC2

#### 1. Login to EC2 machine
```ssh -i aws.pem ec2-user@<PUBLIC_DNS>```

#### 2. Docker installation
```sudo yum install docker -y```

#### 3. Allow ec2-user to run docker without root
```sudo usermod -a -G docker ec2-user```

#### 4. Start docker service
```sudo service docker start```

#### 5. Run docker image in background
```
docker run -u root \
	--rm -d -p 8080:8080 \
	-p 50000:50000 \
	--name myjenkins \
	-v jenkins-data:/var/jenkins_home \
	-v /var/run/docker.sock:/var/run/docker.sock \
	jenkinsci/blueocean
```

#### 6. Run container in interactive mode

```
docker exec -it myjenkins bash
```

#### 7. Copy Jenkins admin installation password (from interactive mode 6.)

```
cat /var/jenkins_home/secrets/initialAdminPassword
```

### Jenkins local installation (Docker + HyperV, Win10)

#### 1. Make sure that Docker Desktop is running
#### 2. Run docker image with Jenkins in background
```
docker run -u root --rm -d -p 8080:8080 -p 50000:50000 --name myjenkins -v jenkins-data:/var/jenkins_home -v //var/run/docker.sock:/var/run/docker.sock jenkinsci/blueocean
```

#### 3. Run container in interactive mode

```
winpty docker exec -it myjenkins bash
```

#### 4. Copy Jenkins admin installation password (from interactive mode 6.)

```
cat /var/jenkins_home/secrets/initialAdminPassword
```

### Copy paste snippets

Image name: `adoptopenjdk/maven-openjdk11`
Docker hub: [link](https://hub.docker.com/r/adoptopenjdk/maven-openjdk11)

Pipeline syntax docs: [link](https://jenkins.io/doc/book/pipeline/syntax/)