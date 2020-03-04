pipeline {
    parameters {
        choice(
                name: 'PROFILE',
                choices: ['local', 'dev', 'custom'],
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
        stage('Install') {
            steps {
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Run') {
            steps {
                dir('simple-backend/target') {
                    sh """
                        java -jar app.jar \
                        --spring.profiles.active=$params.PROFILE \
                        --productName=$params.PRODUCT_NAME
                    """
                }
            }
        }
        stage('Deploy') {
            when {
                expression {
                    return params.PROFILE == 'custom'
                }
            }
            steps {
                dir('simple-backend/target') {
                    sh """
                        java -jar app.jar \
                        --spring.profiles.active=$params.PROFILE \
                        --productName=$params.PRODUCT_NAME \
                        --salesforce.username=$USERNAME \
                        --salesforce.password=$PASSWORD \
                        --salesforce.clientId=$CLIENT_ID \
                        --salesforce.clientSecret=$CLIENT_SECRET \
                    """
                }
            }
        }
    }
}