pipeline {
    agent {
        docker {
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    
    parameters {
        string(name: 'PRODUCT_NAME', defaultValue: 'Mr Jenkins', description: 'Name of the product?')
        choice(name: 'PROFILE', choices: ['local', 'dev', 'other'], description: 'Pick something')
    }
    stages {
        stage('STAGE NAME') {
            steps {
                sh 'ls -a'
                sh 'cd simple-backend && mvn clean install'
            }
        }
        stage('run jar') {
            environment {
                PSWRD = credentials('PASSWORD')
                USERNAME = credentials('USERNAME')
                CLIENT_SECRET = credentials('CLIENT_SECRET')
                CLIENT_ID  = credentials('CLIENT_ID ')
            }
            steps {
                sh "echo pass:$env.PSWRD username:$env.USERNAME secret:$env.CLIENT_SECRET id: $env.CLIENT_ID"
                sh "cd simple-backend/target && java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
            }
        }
    }
}