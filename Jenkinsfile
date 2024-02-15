pipeline {
    agent any

    parameters{
        string(name: 'Database', defaultValue: '', description: 'Database to interact')
        string(name: 'Environment',  defaultValue: '', description: 'Environment where the database is located.')
    }

    options {
        // Timeout counter starts AFTER agent is allocated
        timeout(time: 1, unit: 'SECONDS')
    }
    stages {
        stage('Example') {
            steps {
                echo 'Hello World'
            }
        }
    }
}