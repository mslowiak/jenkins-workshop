pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('Test') {
            steps {
                sh "ls -la"
            }
        }
        stage('Build') {
            steps {
                sh 'cd simple-backend && mvn clean install'
            }
        }
    }
}