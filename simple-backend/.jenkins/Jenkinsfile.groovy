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
                dir('simple-backend/target') {
                    sh 'java -jar app.jar --spring.profiles.active=dev --service.url=http://07b8c8896d9a.ngrok.io'
                }
            }
        }

    }

}


