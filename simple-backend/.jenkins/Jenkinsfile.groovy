pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('STAGE NAME') {
            steps {
                sh 'ls -a'
                sh 'cd simple-backend && mvn clean install'
            }
        }
        stage('run jar') {
            steps {
                sh 'ls -a'
                sh 'cd simple-backend/taret && java -jar app.jar'
            }
        }
    }
}