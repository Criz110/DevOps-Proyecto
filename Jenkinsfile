pipeline {
    agent any

    environment {
        COMPOSE_FILE = "docker-compose.yml"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Stop Previous Containers') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE down || true'
            }
        }

        stage('Build Images') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE build'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker compose -f $COMPOSE_FILE up -d'
            }
        }

        stage('Verify') {
            steps {
                sh 'docker ps'
            }
        }
    }
}