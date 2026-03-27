pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'Java21'
    }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test Execution') {
            steps {
                bat 'mvn test'
            }
        }
    }