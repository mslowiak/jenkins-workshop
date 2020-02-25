pipeline {
    agent{
        docker{
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('First') {
            steps{
                sh "ls -la"
            }
        }
        stage('Second') {
            steps{
                dir('simple-backend'){
                    sh 'mvn clean install'
                }
            }
        }
    }
}