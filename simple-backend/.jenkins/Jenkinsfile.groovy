pipeline {
    agent any
    stages {
        stage('LIST FILES') {
            steps {
                sh 'ls -al'
            }
        }
    }
}