pipeline {
    agent any

    parameters {
        string(
            name: 'IMAGE_TAG',
            defaultValue: 'latest',
            description: 'Tag de imagen a desplegar'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'github-creds',
                    url: 'https://github.com/Criz110/DevOps-Proyecto.git'
            }
        }

        stage('Deploy') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh """
                        echo "Deploying ${params.IMAGE_TAG}"

                        docker compose down --remove-orphans

                        docker compose build

                        IMAGE_TAG=${params.IMAGE_TAG} docker compose up -d
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