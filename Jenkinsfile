pipeline {
    agent any
    tools {
        maven 'apache-maven-3.6.0'
    }
    stages {
        stage('Install') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
