pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    parameters{
        choice(name: 'CHOICE', choices: ['local', 'dev'])
    }
    stages {
        stage('Build') {
            steps {
                dir('simple-backend') {
                    sh 'mvn -s .mvn/settings-plab.xml clean install'
                }
            }
        }
        stage('Run'){
            steps{
                dir('simple-backend/target'){
                    sh 'java -jar app.jar'
                }
            }
        }
    }
}