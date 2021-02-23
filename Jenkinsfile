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
	}
}