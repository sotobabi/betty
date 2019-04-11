pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                checkout scm
                sh "gradle build --stacktrace"
            }
        }
        stage('Test') {
            steps {
                parallel(
                    'check': {
                        sh "gradle check --stacktrace"
                    },
                    'echo': {
                        echo "ok in parallel"
                    }
                )
            }
        }
        stage('Approve') {
            agent none
            steps {
                input "Does the staging environment look ok ?"
            }
        }
        stage('Prod') {
            steps {
                'echo:' {
                    echo "Deploying to production"
                }
            }
        }
    }
}
