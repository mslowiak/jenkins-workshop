pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('ECHO ALL FILE NAMES') {
            steps {
                dir ('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}