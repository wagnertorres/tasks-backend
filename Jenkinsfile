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
                sh "${scannerHome}/bin/sonar-scanner -e"
            }
        }
	}
}