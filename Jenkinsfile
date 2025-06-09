pipeline {
    agent any

    tools {
        jdk 'java-21'
        maven 'mvn-3.8.4'
    }

    environment {
        ALLURE_RESULTS = 'target/allure-results'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning source code...'
                checkout scm
            }
        }

        stage('Run tests') {
            steps {
                echo 'Running Selenium tests...'
                sh 'mvn clean test -DseleniumManagerLogs=true'
            }
        }
    }

    post {
        always {
            echo 'Debug the generated allure reports'
            sh 'ls -l target'
            sh 'ls -l target/allure-results'
            sh '[ -d target/allure-results ] && echo "Allure results exist" || echo "No allure results found"'
            allure includeProperties: false,
                   jdk: 'java-21',
                   commandline: 'allure-2.29.0',
                   results: [[path: "${env.ALLURE_RESULTS}"]]

            echo 'Cleaning workspace...'
            cleanWs()
        }
        success {
            echo '✅ Run tests successful'
        }
        failure {
            echo '❌ Run tests failed'
        }
    }
}
