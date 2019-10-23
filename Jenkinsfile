pipeline {
    agent {
        docker { image 'maven:3.6.2-jdk-12' }
    }
    stages {
        stage('Install') {
            steps {
                sh 'mvn clean install -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn -B'
            }
        }
    }
}
