pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-21-openjdk-amd64'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        DEPLOY_SERVER = 'root@45.138.158.89'
        DEPLOY_PATH = '/root/'
        JAR_NAME = 'jenkins_app.jar'
        LOG_FILE = "/var/log/${JAR_NAME}.log"
        BACKUP_PATH = '/root/backup/'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "📥 Kod GitHub-dan olinmoqda..."
                git branch: 'master', url: 'https://github.com/HusanboyJorayev/jenkins-app.git'
            }
        }

        stage('Build') {
            steps {
                echo "🔨 Maven bilan build qilinmoqda..."
                sh 'chmod +x ./mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                sshagent(['agro_server_key']) {
                    echo "📤 Jar fayl serverga yuborilmoqda..."
                    sh "scp -o StrictHostKeyChecking=no target/${JAR_NAME} ${DEPLOY_SERVER}:${DEPLOY_PATH}"

                    echo "⏹️ Eski jarni backup va to‘xtatish..."
                    sh """
                    ssh -o StrictHostKeyChecking=no ${DEPLOY_SERVER} '
                        mkdir -p ${BACKUP_PATH}
                        if [ -f ${DEPLOY_PATH}${JAR_NAME} ]; then
                            mv ${DEPLOY_PATH}${JAR_NAME} ${BACKUP_PATH}${JAR_NAME}_\$(date +%Y%m%d%H%M%S)
                        fi
                        pkill -f "${JAR_NAME}" || true
                    '
                    """

                    echo "▶️ Yangi jarni ishga tushirish..."
                    sh """
                    ssh -o StrictHostKeyChecking=no ${DEPLOY_SERVER} '
                        nohup java -jar ${DEPLOY_PATH}${JAR_NAME} > ${LOG_FILE} 2>&1 &
                        disown
                    '
                    """
                }
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
