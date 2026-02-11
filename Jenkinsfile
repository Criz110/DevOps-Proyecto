pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

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