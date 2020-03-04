pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('BUILD') {
            steps {
                sh "ls -la"
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('RUN_APP') {
            steps {
                dir('simple-backend/target') {
                    sh "java -jar app.jar"
                }
            }
        }
    }


}