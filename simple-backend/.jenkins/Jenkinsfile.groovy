pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('LIST FILES') {
            steps {
                sh 'ls -al'
                sh 'pwd'
            }
        }
        stage('BUILD') {
            steps {
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('RUN') {
            steps {
                dir('simple-backend/target') {
                    sh 'java -jar app.jar'
                }
            }
        }
    }
}