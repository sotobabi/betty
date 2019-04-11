pipeline {
    agent none

    stages {
        stage('Build') {
            agent { label 'gradle' }
            steps {
                checkout scm
                sh "gradle build --stacktrace"
            }
        }
        stage('Test') {
            agent { label 'gradle' }
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
            agent { label 'gradle' }
            steps {
                'echo:' {
                    echo "Deploying to production"
                }
            }
        }
    }
}
