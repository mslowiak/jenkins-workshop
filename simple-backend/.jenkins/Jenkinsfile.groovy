pipeline {
    agent any
        stages{
            stage('STAGE 1'){
                steps{
                    sh 'ls -al'
                }
            }
        }


}