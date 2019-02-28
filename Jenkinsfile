pipeline {
    agent none
    stages {
        stage('Install') {
            agent {
                docker { image 'maven:3.6.0-jdk-11-slim' }
            }
            steps {
                sh 'git status'
                sh 'mvn clean install -B'
            }
        }
    }
}
