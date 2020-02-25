pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('ECHO ALL FILE NAMES') {
            steps {
                sh "ls -al"
            }
        }
    }
}