pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('STAGE NAME') {
            steps {
                sh 'ls -a'
                sh 'cd simple-backend'
                sh 'mvn clean install'
            }
        }
    }
}