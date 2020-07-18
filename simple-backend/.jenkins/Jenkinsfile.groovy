pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('FIRST STAGE'){
            steps {
                sh 'echo "fobar"'
                sh 'mvn clean install'
            }
        }
    }
}