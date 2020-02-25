pipeline {
    agent any
    stages {
        stage('ECHO ALL FILE NAMES') {
            steps {
                sh "ls -al"
            }
        }
    }
}