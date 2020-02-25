pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
        }
    }
    stages {
        stage('Stage one') {
            steps {
                sh "ls -la"
            }
        }
    }
}