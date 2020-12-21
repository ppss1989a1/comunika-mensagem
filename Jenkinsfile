def projectName="comunika-mensagem"
def clusterName="cluster-devtst"
def namespace="comunika-mensagem"
def imageName="$projectName"
def urlEcr="392721527794.dkr.ecr.us-east-1.amazonaws.com/$imageName"
def ambiente=""
def dockerfilePath="src/main/docker/Dockerfile.jvm"

pipeline {
    agent { 
        docker {
            alwaysPull true
            args "--memory=2048m -u root --privileged -v /var/run/docker.sock:/var/run/docker.sock "
            image "brenzo/awsk8vn:latest" 
        } 
    }
    stages {
        stage("Setup") {
            steps {
                script {
                    if (env.BRANCH_NAME == "master") {
                        ambiente="master"
                        clusterName="cluster-prd"
                        namespace="comunika-mensagem"
                    } else if (env.BRANCH_NAME == "release") {
                        ambiente="release"
                        namespace="comunika-mensagem"
                    } else if (env.BRANCH_NAME == "desenv") {
                        ambiente="desenv"
                        namespace="comunika-mensagem"
                    } else {
                        ambiente="develop"
                    }
                }
                sh '$(aws ecr get-login --no-include-email --region us-east-1)'
                sh "aws eks --region us-east-1 update-kubeconfig --name $clusterName"
                sh "kubectl version"
            }
        }
        stage("Build") {
            steps {
                sh "mvn clean package" 
            }
        }
        stage("Deploy") {
            when {
                anyOf {
                    branch 'master';
                    branch 'release';
                    branch 'develop'
                }
            }
            steps {
                sh "docker build -f $dockerfilePath -t $imageName ."
                sh "docker tag $imageName $urlEcr"
                sh "docker tag $imageName $urlEcr:$ambiente-latest"
                sh "docker tag $imageName $urlEcr:$env.GIT_COMMIT"

                sh "docker push $urlEcr"
                sh "docker push $urlEcr:$ambiente-latest"
                sh "docker push $urlEcr:$env.GIT_COMMIT"
                sh "kubectl set image deployment/$projectName $projectName=$urlEcr:$env.GIT_COMMIT -n $namespace"
            }
        }
    }
}
