pipeline {
    agent any

    parameters{
        string(name: 'Database', defaultValue: '', description: 'Database to interact')
        string(name: 'Environment',  defaultValue: '', description: 'Environment where the database is located.')
        string(name: 'Query',  defaultValue: '', description: 'query to execute')
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

    post {
        always {
            // Bucle para preguntar el parámetro en loop
            script {
                def continuarLoop = true
                while (continuarLoop) {
                    def userInput = input(
                        id: 'replayInput',
                        message: '¿Desea ejecutar nuevamente con un valor diferente?',
                        parameters: [string(name: 'Query', defaultValue: '', description: 'query to execute')]
                    )

                    // Si el usuario decide salir del bucle, establecer continuarLoop a falso
                    if (userInput == null) {
                        continuarLoop = false
                    } else {
                        // Mostrar el valor del parámetro y continuar el bucle
                        echo "El valor ingresado es: ${userInput}"
                    }
                }
            }
        }
    }
}