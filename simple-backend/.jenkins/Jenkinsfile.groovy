pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('mvn clean install') {
            steps {
                dir ('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('java -jar') {
            steps {
                dir ('simple-backend') {
                    sh 'java -jar app.jar'
                }
            }
        }
    }
}