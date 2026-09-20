pipeline {

    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-17'
            args '-v /root/.m2:/root/.m2'
        }
    }

    parameters {

        string(
            name: 'GIT_BRANCH',
            defaultValue: 'main',
            description: 'Branche Git à construire'
        )

        choice(
            name: 'ENV',
            choices: ['dev', 'staging', 'prod'],
            description: 'Environnement cible'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                echo "📦 Récupération de la branche ${params.GIT_BRANCH}"

                git branch: params.GIT_BRANCH,
                    url: 'https://gitlab.com/VOTRE_USER/VOTRE_PROJET.git'
            }
        }

        stage('Build') {
            steps {

                echo "🛠️ Build pour l'environnement : ${params.ENV}"

                sh """
                    echo "Java version:"
                    java -version

                    echo "Maven version:"
                    mvn -version

                    mvn clean compile -P${params.ENV}
                """
            }
        }

        stage('Quality') {

            parallel {

                stage('Unit Tests') {

                    steps {

                        echo '🧪 Exécution des tests unitaires...'

                        sh 'mvn test'
                    }

                    post {

                        always {
                            junit '**/target/surefire-reports/*.xml'
                        }
                    }
                }

                stage('Static Analysis') {

                    steps {

                        echo '🔎 Analyse statique...'

                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }

        stage('Package') {

            steps {

                echo '📦 Génération de l’artefact Maven...'

                sh 'mvn package -DskipTests'

                archiveArtifacts(
                    artifacts: 'target/*.jar',
                    fingerprint: true
                )
            }
        }
    }

    post {

        success {

            echo """
            ✅ PIPELINE RÉUSSI

            Branche      : ${params.GIT_BRANCH}
            Environnement: ${params.ENV}
            Build        : ${env.BUILD_NUMBER}
            """
        }

        failure {

            echo """
            ❌ PIPELINE ÉCHOUÉ

            Branche      : ${params.GIT_BRANCH}
            Environnement: ${params.ENV}
            Build        : ${env.BUILD_NUMBER}
            """
        }

        always {

            echo "🏁 Fin du pipeline"
        }
    }
}