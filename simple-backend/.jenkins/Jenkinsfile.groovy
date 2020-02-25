pipeline {
	agent {
		image 'adoptopenjdk/openjdk11'
	}
	stages {
		stage('STAGE NAME'){
			steps{
				sh "ls -la"
			}
		}
		stage('maven install'){
			steps{
				sh "cd .."
				sh "maven clean install"
			}
		}
	}

}