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
                    sh "java -jar app.jar --spring.profiles.active=${params.PROFILE}"
                }
            }
        }

    }
    parameters{
        choice(name: 'PROFILE', choices: ['dev','local'])
    }

    environment{
        DEV_URL=credentials('url')
    }

}


