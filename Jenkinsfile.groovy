
     
    properties([
        parameters([
            booleanParam(name: 'cmdloop', defaultValue: true, description: 'for loop querys')
            string(name: 'Query',  defaultValue: '', description: 'query to execute')
            string(name: 'Database', defaultValue: '', description: 'Database to interact')
            string(name: 'Environment',  defaultValue: '', description: 'Environment where the database is located.')
        ])
    ])

        stage('user validation') {
            steps {
                def feedback = input(message: "Doyou have acces?", submitterParameter: 'submiterid')
                echo "submiter:${feedback}"
                if(!['javier'].contains(feedback)) {
                    currentBuild.result = 'ABORTED'
                    error("submiter:${feedback} does not have access")
                }

            }
        }
        stage('execute query'){
            echo "database: ${params.Database}"
            while(params.cmdloop == true){
                userInput = input(id: 'userInput', message: 'run querys', parameters: [[$class: 'TextParameter', name: 'qry', defaultValue: params.Query]])
                try{
                    sh "${userInput}"
                }
                catch(Exception ex){
                    println "error: $ex"
                }
            }
        }
