pipeline {
    parameters {
        choice(
                name: 'PROFILE',
                choices: ['local', 'dev', 'other'],
                description: ''
        )
        string(
                name: 'PRODUCT_NAME',
                defaultValue: 'product',
                description: ''
        )
    }
    environment {
        USERNAME = credentials('USERNAME')
        PASSWORD = credentials('PASSWORD')
        CLIENT_ID = credentials('CLIENT_ID')
        CLIENT_SECRET = credentials('CLIENT_SECRET')
    }
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('mvn clean install') {
            steps {
                dir ('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('run') {
            steps {
                dir ('simple-backend/target') {
                    sh """
                        java -jar app.jar \
                        --spring.profiles.active=$params.PROFILE \
                        --productName=$params.PRODUCT_NAME
                    """
                    sh """
                        echo $environment.USERNAME
                        echo $PASSWORD
                        echo $environment.CLIENT_ID
                        echo $CLIENT_SECRET
                    """
                }
            }
        }
    }
}