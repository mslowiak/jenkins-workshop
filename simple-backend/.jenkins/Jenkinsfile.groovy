pipeline {
	agent {
		docker{
			image 'adoptopenjdk/openjdk11'
		}
	}
	stages {
		stage('STAGE NAME'){
			steps{
				sh "ls -la"
			}
		}
		stage('maven install'){
			steps{
				dir('simple-backend'){
					sh "maven clean install"
				}
				
			}
		}
	}

}