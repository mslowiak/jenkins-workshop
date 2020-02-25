pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('TEST') {
            steps {
                sh "ls -la"
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
    }


}