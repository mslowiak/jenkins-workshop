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
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
                dir('simple-backend/target') {
                    sh 'java -jar app.jar'
                }
            }
        }
    }
}