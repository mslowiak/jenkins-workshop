pipeline {
    environment{
        PASSWORD = credentials('PASSWORD')
        CLIENT_SECRET = credentials('CLIENT_SECRET')
        USERNAME = credentials('USERNAME')
        CLIENT_ID = credentials('CLIENT_ID')
        URL = "https://sabre--tncommdp3.cs8.my.salesforce.com/services"
    }
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    parameters{
        choice(name: 'PROFILE', choices: ['local', 'dev', 'custom'])
        string(name: 'PRODUCT_NAME', defaultValue: 'Hello!')
    }
    stages {
        stage('Build') {
            steps {
                dir('simple-backend') {
                    sh 'mvn -s .mvn/settings-plab.xml clean install'
                    echo "$env.PASSWORD"
                }
            }
        }
        stage('Deploy'){
            when {
                expression {
                    params.PROFILE == 'custom'
                }
            }
            steps{
                dir('simple-backend/target') {
                    sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME \
                    --salesforce.username=$env.USERNAME --salesforce.password=$env.PASSWORD \
                    --salesforce.clientId=$env.CLIENT_ID --salesforce.clientSecret=$env.CLIENT_SECRET --salesforce.url=$env.URL"
                }
            }
        }
        stage('Run'){
            when {
                expression {
                    params.PROFILE != 'custom'
                }
            }
            steps{
                dir('simple-backend/target'){
                    sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
                }
            }
        }
    }
}