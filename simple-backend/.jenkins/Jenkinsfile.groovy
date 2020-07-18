pipeline {
    agent any
    stages {
        stage('FIRST STAGE'){
            steps {
                sh 'cat "fobar"'
            }
        }
    }
}