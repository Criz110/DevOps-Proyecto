pipeline {
    agent any

    environment {
        IMAGE_TAG = "${env.BUILD_NUMBER}-${env.GIT_COMMIT.take(7)}"
    }

    stages {

        stage('Build & Deploy') {
            steps {
                dir('Servicios/Ejemplo-Microservicios') {
                    sh """
                        export IMAGE_TAG=${IMAGE_TAG}
                        docker compose down
                        docker compose up -d --build
                    """
                }
            }
        }

    }
}