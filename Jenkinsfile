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

        stage('Deploy Fast') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh """
                        echo "Deploying ${IMAGE_TAG}"

                        # Construye solo si hay cambios (usa cache)
                        docker compose build --parallel

                        # Levanta sin destruir lo que no cambió
                        docker compose up -d
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