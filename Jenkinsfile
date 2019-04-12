pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh "./gradlew build --stacktrace"
            }
        }
        stage('Test') {
            steps {
                parallel(
                    'check': {
                        sh "./gradlew check --stacktrace"
                    },
                    'echo': {
                        echo "ok in parallel"
                    }
                )
            }
        }
        stage('Deploy') {
            steps {
                echo "Deploying to production"
            }
        }
    }
}
