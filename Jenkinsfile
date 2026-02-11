pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                deleteDir()
                checkout scm
            }
        }

        stage('Build Microservices (Maven)') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh 'mvn clean package -DskipTests'
                }
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