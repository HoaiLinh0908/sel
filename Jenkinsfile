pipeline {
    agent { label 'linux-container' }

    tools {
        maven 'mvn-3.8.4'
    }

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Browser to run tests')
        choice(name: 'TEST_SUITE', choices: ['the.internet.tests.xml'], description: 'Test suite to run')
        string(name: 'TEST', defaultValue: '', description: 'Test to run (this setting will override the TEST_SUITE)')
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning source code...'
                checkout scm
            }
        }

        stage('Start The Internet') {
            steps {
                sh 'docker rm -f the-internet'
                sh 'docker pull gprestes/the-internet:v2.6.5'
                sh 'docker run --name the-internet --network jenkins -d -p 7080:5000 gprestes/the-internet:v2.6.5'
            }
        }

        stage('Set environment variables') {
            steps {
                echo 'Setting environment variables before running tests...'
                script {
                    env.BROWSER = params.BROWSER
                    env.THE_INTERNET_URL = 'http://the-internet:5000'
                }
            }
        }

        stage('Run tests') {
            steps {
                script {
                    def mvnCmd = ''
                    if (params.TEST?.trim()) {
                        echo "Running the test: ${params.TEST}"
                        mvnCmd = "mvn clean -Dtest=${params.TEST} test"
                    } else {
                        echo "Running test suite: ${params.TEST_SUITE}"
                        mvnCmd = "mvn clean test -DsuiteXmlFile=src/test/resources/${params.TEST_SUITE}"
                    }
                    sh mvnCmd
                }
            }
        }
    }

    post {
        always {
            sh '[ -d target/allure-results ] && echo "Allure results exist" || echo "No allure results found"'
            allure includeProperties: false,
                   jdk: 'java-21',
                   commandline: 'allure-2.29.0',
                   results: [[path: 'target/allure-results']]
            echo 'Destroy the local The Internet...'
            sh 'docker rm -f the-internet'

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
