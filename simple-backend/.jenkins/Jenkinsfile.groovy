pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
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