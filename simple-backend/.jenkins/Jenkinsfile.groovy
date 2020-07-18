pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2' // save cache to run faster 
        }
    }
    stages {
        stage('FIRST STAGE'){
            steps {
                sh 'echo "fobar"'
                sh 'cd simple-backend && mvn clean install'
            }
        }
    }
}