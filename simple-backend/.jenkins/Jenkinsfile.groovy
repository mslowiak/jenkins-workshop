pipeline {
    parameters {
        string(name: 'URL', defaultValue: 'http://07b8c8896d9a.ngrok.io')
        choice(name: 'PROFILE', choices: ['dev','local'])
    }
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2' // save cache to run faster 
        }
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
                sh 'cd simple-backend/target && java -jar app.jar --spring.profiles.active=${params.PROFILE} --service.url=${params.URL}'
            }
        }
    }
}