# Projeto: Aplicação com RabbitMQ e MySQL na AWS:

# Passos principais:
1. Configurar o Docker Compose para MySQL e RabbitMQ.
2. Criar Dockerfile para a aplicação Spring Boot.
3. Configurar AWS ECS para rodar os containers.
4. Configurar AWS RDS para rodar o MySQL.
5. Automatizar o deploy com GitHub Actions.

#1. Docker Compose para RabbitMQ e MySQL
# Arquivo docker-compose.yml:
services:
    rabbitmq:
        image: rabbitmq:3-management
        ports:
            - "5672:5672"
            - "15672:15672"
    environment:
        RABBITMQ_DEFAULT_USER: user
        RABBITMQ_DEFAULT_PASS: password

    mysql:
    image: mysql:8
    environment:
        MYSQL_DATABASE: produtoDB
        MYSQL_ROOT_PASSWORD: secret
        MYSQL_USER: user
        MYSQL_PASSWORD: password
    ports:
        - "3306:3306"
#2. Dockerfile para a aplicação Spring Boot:

# Usar uma imagem do JDK
FROM openjdk:17-jdk-slim

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

#3. Configuração do AWS ECS (Elastic Container Service)
- Criar um Cluster ECS.
- Configurar um Task Definition no ECS para rodar os containers do Spring Boot, RabbitMQ e 
  MySQL (ou usar RDS para MySQL).
- Subir a imagem da aplicação Spring Boot no Amazon ECR (Elastic Container Registry).
- Definir políticas de escalabilidade e configuração de Auto Scaling.

#4. Configuração do MySQL no Amazon RDS:
- Criar uma instância Amazon RDS para o MySQL.
- Configurar o banco de dados, criar o schema e atualizar as propriedades 
  da aplicação Spring Boot para conectar ao RDS.

#Arquivo application.properties:
spring.datasource.url=jdbc:mysql://{AWS_RDS_ENDPOINT}:3306/produtoDB
spring.datasource.username=user
spring.datasource.password=password

spring.rabbitmq.host=rabbitmq
spring.rabbitmq.port=5672

#5. Automatizando o Deploy com GitHub Actions:
name: Deploy to AWS ECS
on:
    push:
        branches:
            - main
jobs:
    build:
        runs-on: ubuntu-latest

        steps:
        - name: Check out the repo
        - uses: actions/checkout@v2
    
        - name: Set up JDK 17
         uses: actions/setup-java@v1
        with:
            java-version: '17'

        - name: Build with Maven
         run: mvn clean install

        - name: Log in to Amazon ECR
        id: login-ecr
        uses: aws-actions/amazon-ecr-login@v1

        - name: Build, tag, and push image to Amazon ECR

        run: |
            IMAGE_TAG=latest
            docker build -t ${{ secrets.AWS_ECR_REPO_NAME }}:$IMAGE_TAG .
            docker tag ${{ secrets.AWS_ECR_REPO_NAME }}:$IMAGE_TAG ${{ secrets.AWS_ACCOUNT_ID }}.dkr.ecr.${{ secrets.AWS_REGION }}.amazonaws.com/${{ secrets.AWS_ECR_REPO_NAME }}:$IMAGE_TAG
            docker push ${{ secrets.AWS_ACCOUNT_ID }}.dkr.ecr.${{ secrets.AWS_REGION }}.amazonaws.com/${{ secrets.AWS_ECR_REPO_NAME }}:$IMAGE_TAG

        - name: Deploy to ECS
        uses: aws-actions/amazon-ecs-deploy-task-definition@v1
        with:
            task-definition: ecs-task-definition.json
            service: my-ecs-service
            cluster: my-ecs-cluster
            wait-for-service-stability: true

Resumo das Etapas:
- Docker Compose configura RabbitMQ e MySQL localmente.
- Dockerfile empacota a aplicação Spring Boot.
- AWS ECS gerencia containers e escalabilidade.
- AWS RDS hospeda o banco MySQL.
- GitHub Actions automatiza o build, push para o ECR e deploy no ECS.