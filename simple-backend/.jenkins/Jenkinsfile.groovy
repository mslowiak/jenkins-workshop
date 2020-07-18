pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('LIST FILES') {
            steps {
                sh 'ls -al'
            }
            steps {
                sh 'mvn clean install'
            }
        }
    }
}