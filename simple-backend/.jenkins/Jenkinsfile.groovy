pipeline {
    agent any
    stages {
        stage('Stage one') {
            steps {
                sh "ls -la"
            }
        }
    }
}