pipeline {

    agent any

    parameters {

        string(
            name: 'BRANCH_NAME',
            defaultValue: 'main',
            description: 'Branche à construire'
        )

        choice(
            name: 'ENV',
            choices: ['dev', 'staging', 'prod'],
            description: 'Environnement cible'
        )
    }

    stages {

        stage('Build') {

            steps {

                echo "🛠️ Build de la branche ${params.BRANCH_NAME} pour ${params.ENV}"

                sh "mvn clean compile -P${params.ENV}"
            }
        }

        stage('Test') {

            steps {

                echo '✅ Exécution des tests unitaires...'

                sh 'mvn test'
            }
        }
    }
}