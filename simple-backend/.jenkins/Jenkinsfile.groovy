pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    parameters {
        choice(name: 'profile', choices: ['dev'])
        string(name: 'url', defaultValue: 'http://07b8c8896d9a.ngrok.io')
    }

    stages {
        stage('LIST FILES') {
            steps {
                sh 'ls -al'
                sh 'pwd'
            }
        }
        stage('BUILD') {
            steps {
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('RUN') {
            steps {
                dir('simple-backend/target') {
                    sh 'java -jar app.jar --spring.profiles.active=$params.profile --service.url=$params.url'
                }
            }
        }
    }
}