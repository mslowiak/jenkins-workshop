pipeline {
    agent {
        image 'adoptopenjdk/openjdk11'
    }
    stages {
        stage('Build') {
            steps {
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
    }
}