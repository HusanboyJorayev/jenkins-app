pipeline {
    agent any

    tools {
        maven 'maven_3_9_11'   // Jenkins'dagi Maven tool nomi
    }

    environment {
        DOCKER_IMAGE = 'husanboyjorayevv/jenkins-service'  // DockerHub image nomi
        APP_NAME = 'jenkins-service'                // Docker konteyner nomi
        APP_PORT = '8765'                           // Ilova porti
        DOCKER_HOST = 'unix:///var/run/docker.sock' // Lokal docker daemon
        BUILD_NUMBER = "${env.BUILD_NUMBER}"        // Jenkins build raqami
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
                sh "docker -H $DOCKER_HOST build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} ."

                echo "📤 DockerHub-ga push qilinmoqda..."
                sh "docker login -u husanboyjorayevv -p husanboy2828"
                sh "docker push ${DOCKER_IMAGE}:${BUILD_NUMBER}"
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
