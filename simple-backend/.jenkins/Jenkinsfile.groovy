pipeline {
    agent {
        image 'adoptopenjdk/openjdk11'
    }
    stages {
        stage('maven install'){
            sh "cd .."
            sh "maven clean install"
        }
    }
}