pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2' // save cache to run faster 
        }
    }
    environment {
        DEV_URL = credentials('URL')
    }
    parameters {
        choice(name: 'PROFILE', choices: ['dev','local'])
    }
    stages {
        stage('BUILD'){
            steps {
                sh 'echo "fobar"'
                sh 'cd simple-backend && mvn clean install'
            }
        }
        stage('RUN'){
            steps{
                sh "cd simple-backend/target && java -jar app.jar --spring.profiles.active=${params.PROFILE}"
            }
        }
    }
}