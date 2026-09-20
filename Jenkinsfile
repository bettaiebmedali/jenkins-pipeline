pipeline {

    agent {
        dockerContainer {
            image 'maven:3.9.9-eclipse-temurin-17'
        }
    }

    stages {

        stage('Environment') {
            steps {
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -version'
            }
        }
    }
}