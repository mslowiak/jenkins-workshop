pipeline {
    agent any
    stages {
        stage('First') {
            steps{
                sh "ls -la"
            }
        }
    }
}