pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Stage one') {
            steps {
                sh "ls -la"
            }
        }
        stage('Stage two') {
            steps {
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Stage three') {
            steps {
                dir('simple-backend/target') {
                    sh 'java -jar app.jar'
                }
            }
        }
    }
}