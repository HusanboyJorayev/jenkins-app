pipeline {
    agent any

    tools {
        maven 'maven_3_9_11'
    }

    environment {
        DOCKER_IMAGE = 'husanboyjorayevv/jenkins-service'
        APP_NAME = 'jenkins-service'
        APP_PORT = '8765'
        DOCKER_HOST = 'unix:///var/run/docker.sock'
        MONITORING_COMPOSE = '/home/jenkins/monitoring/docker-compose.yml' // Monitoring docker-compose yo‘li
    }

    stages {
        stage('Checkout & Build') {
            steps {
                echo "🔨 Maven bilan build qilinmoqda..."
                checkout scm
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build & Push') {
            steps {
                echo "🐳 Docker image yaratilmoqda..."
                withCredentials([usernamePassword(credentialsId: 'dockerhub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh """
                        docker -H $DOCKER_HOST build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .
                        docker login -u $DOCKER_USER -p $DOCKER_PASS
                        docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}
                    """
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                echo "🚀 Konteyner ishga tushirilmoqda..."
                sh """
                    docker stop ${APP_NAME} || true
                    docker rm ${APP_NAME} || true
                    docker run -d --name ${APP_NAME} -p ${APP_PORT}:${APP_PORT} ${DOCKER_IMAGE}:${BUILD_NUMBER}
                """
            }
        }

         stage('Start Monitoring') {
             steps {
                 echo "📊 Monitoring servislarini ishga tushurish..."
                 // Docker Compose V2 bilan ishga tushirish
                 sh "docker compose -f ${MONITORING_COMPOSE} up -d"
             }
         }
    }

    post {
        success {
            echo "✅ Deployment muvaffaqiyatli yakunlandi!"
        }
        failure {
            echo "❌ Deploymentda xatolik yuz berdi!"
        }
    }
}

