pipeline {
    environment {
        PASSWORD = credentials('PASSWORD')
        CLIENT_SECRET = credentials('CLIENT_SECRET')
        USERNAME = credentials('USERNAME')
        CLIENT_ID = credentials('CLIENT_ID')
    }
    parameters {
        choice(name: 'PROFILE', choices: ['local', 'dev', 'other'], description: 'proooofiles')
        string(name: 'PRODUCT_NAME', defaultValue: 'TicketExpress', description: 'poduuuct name')
    }
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Test') {
            steps {
                sh "echo XXXXXXXXXXXXXXXxxxx $env.PASSWORD"
                sh "ls -la"
            }
        }
        stage('Build') {
            steps {
                sh 'cd simple-backend && mvn -s .mvn/settings-plab.xml clean install'
            }
        }
        stage('Dev'){
            when{
                expression {
                    params.PROFILE == "other"
                }
            }
            steps {
                dir('simple-backend/target'){
                    sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME \
                        --salesforce.username=$env.USERNAME --salesforce.password=$env.PASSWORD --salesforce.clientId=$env.CLIENT_ID \
                        --salesforce.clientSecret=$env.CLIENT_SECRET"
                }
            }
        }
        stage('Run'){
            when{
                expression {
                    params.PROFILE != "other"
                }
            }
            steps {
                dir('simple-backend/target'){
                    sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
                }
            }
        }
    }
}