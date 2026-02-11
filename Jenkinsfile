pipeline {
    agent any

    stages {

        stage('Deploy Microservices') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh 'docker compose down || true'
                    sh 'docker compose up -d --build'
                }
            }
        }

        stage('Verify') {
            steps {
                sh 'docker ps'
            }
        }
    }
}