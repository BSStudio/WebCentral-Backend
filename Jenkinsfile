pipeline {
    agent docker
    tools {
        maven 'apache-maven-3.6.0'
        jdk 'jdk-11.0.2'
    }
    stages {
        stage('Install') {
            steps {
                sh 'echo JAVA_HOME=$JAVA_HOME'
                sh 'export PATH=$JAVA_HOME/bin:$PATH'
                sh 'mvn clean install'
            }
        }
    }
}
