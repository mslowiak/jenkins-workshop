pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    environment {
        DEV_PASSWORD = credentials('PASSWORD')
        DEV_USERNAME = credentials('USERNAME')
        DEV_CLIENT_SECRET = credentials('CLIENT_SECRET')
        DEV_CLIENT_ID = credentials('CLIENT_ID')
    }
    parameters {
        choice(name: 'PROFILE', choices: ['local', 'dev', 'custom'], description: '')
        string(name: 'PRODUCT_NAME', defaultValue: 'dupa', description: '')
        string(name: 'URL', defaultValue: '', description: '')
    }
    stages {
        stage('Stage one') {
            steps {
                sh "ls -la"
            }
        }
        stage('Stage two') {
            steps {
                dir('simple-backend') {
                    sh 'mvn clean install'
                }
            }
        }
        stage('Run OTHER') {
            when {
                expression {
                    PROFILE == 'custom'
                }
            }
            steps {
                dir('simple-backend/target') {
                    sh """java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME \
                            --salesforce.username=$env.DEV_USERNAME --salesforce.password=$env.DEV_PASSWORD \
                            --salesforce.clientId=$env.DEV_CLIENT_ID --salesforce.clientSecret=$env.DEV_CLIENT_SECRET \
                            --salesforce.url=$params.URL
                        """
                }
            }
        }
        stage('Run ') {
            when {
                expression {
                    PROFILE != 'custom'
                }
            }
            steps {
                dir('simple-backend/target') {
                    sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
                }
            }
        }
    }
}
