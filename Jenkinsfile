pipeline {
    agent any

    options {
        skipDefaultCheckout()
    }

    tools {
        maven 'MAVEN_HOME'
    }

    environment {
        DOCKER_PROJECT_NAME = 'lp-app'
        APP_CONTAINER_NAME = 'lp-app'
    }

    stages {
        stage('Clone') {
            steps {
                echo '📥 Clonando el repositorio...'
                git branch: 'main', url: 'https://github.com/DiegoAlonso26/IntegracionyDespliegueContinuo.git'
                echo '✅ Clonación completada.'
            }
        }

        stage('Build') {
            steps {
                echo '🔧 Compilando el proyecto Java...'
                sh 'mvn clean package -DskipTests'
                echo '✅ Build completado.'
            }
        }

        stage('Test') {
            steps {
                echo '🧪 Ejecutando pruebas unitarias...'
                sh 'mvn test'
                echo '✅ Pruebas completadas.'
            }
        }

        stage('Sonar Analysis') {
            steps {
                echo '📊 Enviando análisis a SonarQube...'
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar -Pcoverage'
                }
                echo '✅ Análisis de SonarQube enviado.'
            }
        }

        stage('Quality Gate') {
            steps {
                echo '🎯 Esperando veredicto de SonarQube...'
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
                echo '✅ Quality Gate aprobado.'
            }
        }

        stage('Deploy with Docker') {
            steps {
                echo '🚀 Iniciando despliegue con Docker Compose...'
                script {
                    try {
                        sh "docker-compose -p ${DOCKER_PROJECT_NAME} down --remove-orphans"
                        echo '🧹 Contenedores anteriores eliminados.'
                    } catch (Exception e) {
                        echo "⚠️ No se pudo eliminar el despliegue anterior: ${e.getMessage()}"
                    }

                    sh "docker-compose -p ${DOCKER_PROJECT_NAME} up -d --build"
                    echo '✅ Contenedores levantados correctamente.'

                    sleep(10)
                    echo '📄 Mostrando últimos logs del contenedor de aplicación:'
                    sh "docker logs --tail 50 ${APP_CONTAINER_NAME}"
                }
            }
        }
    }

    post {
        success {
            echo '🎉 Pipeline ejecutado correctamente.'
        }
        failure {
            echo '💥 Falló el pipeline.'
        }
        always {
            echo '📦 Pipeline finalizado.'
        }
    }
}
