pipeline {
    agent any

    tools {
        maven 'Maven3'   // same name as configured in Jenkins
        jdk 'Java21'    // same name as configured in Jenkins
    }

    stages {

        stage('Checkout Code') {
            steps {
                git 'https://github.com/gauribavdhankar/orangeHRM-HybridFramework-Automation'
            }
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
}