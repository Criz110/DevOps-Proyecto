pipeline {
    agent any

    stages {
        stage('Analisis') {
            steps {
                echo 'Hello World'
            }
        }
        stage('DBDeploy') {
            steps {
                sh "docker ps"
            }
        }
    }
}