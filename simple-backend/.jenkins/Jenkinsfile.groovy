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
                sh 'pwd'
                sh 'mvn clean install'
            }
        }
    }
}