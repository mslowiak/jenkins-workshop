pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
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