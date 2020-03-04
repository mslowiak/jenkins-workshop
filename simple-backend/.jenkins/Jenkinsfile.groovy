pipeline {
	parameters{
		choice(name: 'PROFILE', choices: ['local', 'dev', 'other'], description: '')
		string(name: 'PRODUCT_NAME', defaultValue: 'staging', description: '')
	}
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
		stage('java run'){
			steps{
				dir('simple-backend/target'){
					sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
				}
			}
		}
	}

}