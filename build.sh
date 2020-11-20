#!/bin/bash
aws --profile mfa ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 580479692653.dkr.ecr.us-east-1.amazonaws.com
docker build -f Dockerfile -t comunika-mensagem .
docker tag comunika-mensagem:latest 580479692653.dkr.ecr.us-east-1.amazonaws.com/comunika-mensagem:latest
docker push 580479692653.dkr.ecr.us-east-1.amazonaws.com/comunika-mensagem:latest
aws ecs update-service --force-new-deployment --cluster comunika --service comunika-mensagem