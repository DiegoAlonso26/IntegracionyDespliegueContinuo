pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME' // Asegúrate de tener esta herramienta configurada en Jenkins
    }

    stages {
        stage('Clone') {
            steps {
                cleanWs()
                git branch: 'pruebas', url: 'https://github.com/Romulozz/lp.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Sonar Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar -Pcoverage'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }


    }


    }

    post {
        success {
            echo '✅ Pipeline ejecutado correctamente.'
        }
        failure {
            echo '❌ Falló el pipeline.'
        }
    }
