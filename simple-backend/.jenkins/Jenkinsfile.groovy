pipeline {
    agent any
    stages {
        stages('Test'){
            steps{
                sh 'ls -la'
            }
        }
    }
}