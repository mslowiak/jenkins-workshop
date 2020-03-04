pipeline {
    agent{
        docker{
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment{
        ENV_PASSWORD = credentials('PASSWORD')
        ENV_USERNAME = credentials('USERNAME')
        ENV_CLIENT_SECRET = credentials('CLIENT_SECRET')
        ENV_CLIENT_ID = credentials('CLIENT_ID')
    }
    parameters {
        string(name: 'PRODUCT_NAME', defaultValue: 'Mr Jenkins', description: 'Product Name')
        choice(name: 'PROFILE', choices: ['local', 'dev', 'other'], description: 'Pick something')
    }
    stages {
        stage('First') {
            steps{
                sh "ls -la"
            }
        }
        stage('Second') {
            steps{
                dir('simple-backend'){
                    sh 'mvn clean install'
                }
            }
        }
        stage('Echo'){
            steps{
                echo "$env.ENV_PASSWORD"
                echo "$env.ENV_USERNAME"
                echo "$env.ENV_CLIENT_SECRET"
                echo "$env.ENV_CLIENT_ID"
            }
        }
        stage('Run app'){
            steps{
                dir('simple-backend/target'){
                    sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
                }
            }
        }
    }
}