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
                    sh "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBackEnd -Dsonar.host.url=http://localhost:9000 -Dsonar.login=d50589ec2a8a59cb69964bc6e525c7171ce385da -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**.**/src/test/**.**/model/**.**/Application.java"
                }
            }
        }
	}
}