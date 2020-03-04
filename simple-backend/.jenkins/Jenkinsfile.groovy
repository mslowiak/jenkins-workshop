pipeline {
    environment {
        PASSWORD = credentials('PASSWORD')
        USERNAME = credentials('USERNAME')
        CLIENT_SECRET = credentials('CLIENT_SECRET')
        CLIENT_ID = credentials('CLIENT_ID')

    }
    parameters {
        string(name: 'PRODUCT_NAME', defaultValue: 'product name', description: 'xxxxxx')
        choice(name: 'PROFILE', choices: ['local', 'dev', 'other'], description: '')

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
        stage('RUN_APP') {
            steps {
                echo PASSWORD
                echo CLIENT_ID
                echo CLIENT_SECRET
                echo USERNAME
                dir('simple-backend/target') {
                    sh """java -jar app.jar \
                        --productName=$params.PRODUCT_NAME \
                        --profiles.local=$params.PROFILE"""
                }
            }
        }
    }


}