pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'cd simple-backend && mvn clean install'
            }
        }
        stage('Start') {
            steps {
                sh 'cd simple backend/target &&  java -jar app.jar'
            }
        }
    }



}


