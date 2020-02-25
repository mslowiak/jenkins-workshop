pipeline {
	agent any
	stages {
		stage('STAGE NAME'){
			steps{
				sh "ls -la"
			}
		}
	}
}