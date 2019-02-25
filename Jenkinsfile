pipeline {
    agent any
    tools {
        maven 'apache-maven-3.6.0'
    }
    stages {
        stage('Install') {
            steps {
              withMaven(jdk: 'JAVA_HOME')
                sh 'echo JAVA_HOME=$JAVA_HOME'
                sh 'mvn clean install'
            }
        }
    }
}
