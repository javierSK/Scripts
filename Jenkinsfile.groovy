pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                sh'''
                kubectl apply -f deployment.yaml
                '''
            }
        }
    }
}