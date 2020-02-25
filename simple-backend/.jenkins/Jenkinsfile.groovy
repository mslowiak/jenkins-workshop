pipeline {
    agent any {
        stages {
            stage('ECHO ALL FILE NAMES') {
                steps {
                    script {
                        def files = findFiles(glob: '**/*')
                        for (FileWrapper file : files) {
                            echo file.name
                        }
                    }
                }
            }
        }
    }
}