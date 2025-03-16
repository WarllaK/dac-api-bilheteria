# Usar a imagem base do Maven para compilar o projeto
FROM maven:3.8.5-openjdk-17 as builder

# Diretório de trabalho dentro do container para a build
WORKDIR /app

# Copiar o arquivo de configuração do Maven (pom.xml) e arquivos de código fonte
COPY pom.xml .
COPY src ./src

# Rodar o comando de build para criar o arquivo JAR
RUN mvn package -DskipTests

# Usar a imagem base do OpenJDK para rodar o JAR FROM openjdk:17-jdk-alpine # Use an official JDK base image for building
FROM amazoncorretto:17-alpine3.16-jdk AS build

# Diretório de trabalho dentro do container
WORKDIR /app


# Copiar o JAR gerado pela etapa de build para o novo container
COPY --from=builder /app/target/*.jar /app/app.jar

# Expor a porta usada pela aplicação
EXPOSE 8082

# Comando para executar a aplicação
CMD ["java", "-jar", "/app/app.jar"]