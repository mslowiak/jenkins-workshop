pipeline {
	parameters{
		choice(name: 'PROFILE', choices: ['local', 'dev', 'custom'], description: '')
		string(name: 'PRODUCT_NAME', defaultValue: 'staging', description: '')
		string(name: 'URI', defaultValue: 'https://sabre--tncommdp3.cs8.my.salesforce.com/services')
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
		stage('java run '){
			when{
				expression {
					params.PROFILE != 'custom'
				}
			}
			steps{
				dir('simple-backend/target'){
					sh "java -jar app.jar --spring.profiles.active=$params.PROFILE --productName=$params.PRODUCT_NAME"
				}
			}
		}
		stage('java run custom'){
			when{
				expression {
					params.PROFILE == 'custom'
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
					--salesforce.clientSecret=$CLIENT_SECRET \
					--salesforce.url=$URI"
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