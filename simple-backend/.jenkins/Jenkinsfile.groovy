pipeline {
    agent{
        docker{
            image 'adoptopenjdk/maven-openjdk11'
            args '-v /root/.m2:/root/.m2'
        }
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
        stage('Run app'){
            steps{
                dir('simple-backend/target'){
                    sh 'java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME'
                }
            }
        }
    }
}