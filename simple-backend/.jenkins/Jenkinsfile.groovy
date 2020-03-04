pipeline {
	agent {
		docker{
			image 'adoptopenjdk/maven-openjdk11'
			args '-v /root/.m2:/root/.m2'
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
					sh "mvn clean install"
				}
				
			}
		}
	}

}