pipeline {
 	agent any //agente de execução
	stages {
		stage ('Build Backend') {
			steps {
				sh 'mvn clean package -DskipTests=true'
			}
		}
		stage ('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackEnd -Dsonar.host.url=http://localhost:9000 -Dsonar.login=f95ce0c25e5debb4aa1cb2a6818d47e852ce2f8f -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**.**/src/test/**.**/model/**.**/Application.java"
                }
            }
        }
        stage ('Quality Gate') {
            steps {
                sleep(30)
                timeout(time: 1, unit: 'MINUTES'){
                waitForQualityGate abortPipeline: true
                }
            }
        }
	}
}