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
        COMPOSE_PROJECT_NAME = "microservices"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/Criz110/DevOps-Proyecto.git'
            }
        }

        stage('Deploy Selected Services') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh """
                        echo "Deploying version: ${IMAGE_TAG}"

                        docker compose -f docker-compose.yml \\
                        -p ${COMPOSE_PROJECT_NAME} \\
                        up -d \\
                        eureka-server auth-service cliente-service saludo-service api-gateway
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