pipeline {
    agent{
        docker{
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
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