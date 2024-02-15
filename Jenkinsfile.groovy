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
                    def feedback = input(message: "Doyou have access?", submitterParameter: 'submiterid')
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
                        def TextParameter = input(
                            id: 'TextParameter',
                            message: 'run queries',
                            parameters: [
                                [$class: 'TextParameter', name: 'qry', defaultValue: params.Query]
                            ]
                        )
                        try {
                            sh "${TextParameter}"
                        } catch (Exception ex) {
                            println "error: $ex"
                        }
                    }
                }
            }
        }
    }
}
