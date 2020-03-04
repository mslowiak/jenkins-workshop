pipeline {
	parameters{
		choice(name: 'PROFILE', choices: ['local', 'dev', 'other'], description: '')
		string(name: 'PRODUCT_NAME', defaultValue: 'staging', description: '')
	}
	environment{
		PASSWORD = credentials('PASSWORD')
		USERNAME = credentials('USERNAME')
		CLIENT_SECRET = credentials('CLIENT_SECRET')
		CLIENT_ID = credentials('CLIENT_ID')

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
			when{
				expression {
					$params.PROFILE != 'other'
				}
			}
			steps{
				dir('simple-backend/target'){
					sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
				}
			}
		}
		stage('java run other than other'){
			when{
				expression {
					$params.PROFILE == 'other'
				}
			}
			steps{
				dir('simple-backend/target'){
					sh "java -jar app.jar \
					--spring.profiles.active=$params.PROFILE \
					--productName=$params.PRODUCT_NAME \
					--salesforce.username=$USERNAME \
					--salesforce.password=$PASSWORD \
					--salesforce.clientId=$CLIENT_ID \
					--salesforce.clientSecret=$CLIENT_SECRET"
				}
			}
		}
		stage('print env variables'){
			steps{

				sh "echo $PASSWORD"

			}
		}
	}

}