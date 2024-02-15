pipeline {
    agent any

    parameters {
        string(name: 'Database', defaultValue: '', description: 'Database to interact')
        string(name: 'Environment',  defaultValue: '', description: 'Environment where the database is located.')
        booleanParam(name: 'cmdloop', defaultValue: true, description: 'for loop queries')
        string(name: 'Query',  defaultValue: '', description: 'query to execute')
    }

    stages {
        stage('user validation') {
            steps {
                script {
                    def feedback = input(message: "Do you have access?", submitterParameter: 'submitterid')
                    echo "submitter: ${feedback}"
                    if (!['Bryans Javier Suarez Keim', 'Javier', 'javier'].contains(feedback)) {
                        currentBuild.result = 'ABORTED'
                        error("submitter: ${feedback} does not have access")
                    }
                }
            }
        }

        stage('execute query') {
            steps {
                script {
                    echo "database: ${params.Database}"
                    while (params.cmdloop == true) {
                        def userInput = input(
                            id: 'userInput',
                            message: 'run queries',
                            parameters: [
                                [$class: 'TextParameterValue', name: 'qry', value: params.Query]
                            ]
                        )
                        try {
                            sh "${userInput}"
                        } catch (Exception ex) {
                            println "error: $ex"
                        }
                    }
                }
            }
        }
    }
}
