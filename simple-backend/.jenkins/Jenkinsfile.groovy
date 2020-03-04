pipeline {
    environment {
        DEV_PASSWORD = credentials('PASSWORD')
        DEV_USERNAME = credentials('USERNAME')
        DEV_CLIENT_SECRET = credentials('CLIENT_SECRET')
        DEV_CLIENT_ID = credentials('CLIENT_ID')

    }
    parameters {
        string(name: 'PRODUCT_NAME', defaultValue: 'product name', description: 'xxxxxx')
        choice(name: 'PROFILE', choices: ['local', 'dev', 'custom'], description: '')

    }
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('BUILD') {
            steps {
                sh "ls -la"
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('DEPLOY') {
            when {
                expression {
                    PROFILE == 'custom'
                }
            }
            steps {
                dir('simple-backend/target') {
                    sh """java -jar app.jar \
                        --productName=$params.PRODUCT_NAME \
                        --profiles.local=$params.PROFILE \
                        --salesforce.username=$DEV_USERNAME \
                        --salesforce.password=$DEV_PASSWORD \
                        --salesforce.clientId=$DEV_CLIENT_ID \
                        --salesforce.clientSecret=$DEV_CLIENT_SECRET"""
                }
            }
        }
        stage('RUN_APP') {
            steps {
                echo DEV_PASSWORD
                echo DEV_CLIENT_ID
                echo DEV_CLIENT_SECRET
                echo DEV_USERNAME
                dir('simple-backend/target') {
                    sh """java -jar app.jar \
                        --productName=$params.PRODUCT_NAME \
                        --profiles.local=$params.PROFILE"""
                }
            }
        }
    }


}