pipeline {
    agent any

    parameters {
        string(
            name: 'IMAGE_TAG',
            defaultValue: 'latest',
            description: 'Tag de imagen a desplegar'
        )
    }

    environment {
        IMAGE_TAG = "${params.IMAGE_TAG}"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/Criz110/DevOps-Proyecto.git'
            }
        }

        stage('Build Images') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh """
                        echo "Building images with tag ${IMAGE_TAG}"

                        docker build -t ejemplo/eureka-server:${IMAGE_TAG} ./eureka-server
                        docker build -t ejemplo/auth-service:${IMAGE_TAG} ./auth-service
                        docker build -t ejemplo/cliente-service:${IMAGE_TAG} ./cliente-service
                        docker build -t ejemplo/saludo-service:${IMAGE_TAG} ./saludo-service
                        docker build -t ejemplo/api-gateway:${IMAGE_TAG} ./api-gateway
                    """
                }
            }
        }

        stage('Deploy') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh """
                        echo "Deploying ${IMAGE_TAG}"

                        docker compose down --remove-orphans
                        IMAGE_TAG=${IMAGE_TAG} docker compose up -d
                    """
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