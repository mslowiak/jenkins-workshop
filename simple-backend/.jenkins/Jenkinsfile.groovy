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
                sh 'mvn clean install'
            }
        }
    }
}