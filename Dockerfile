# Use a imagem base do OpenJDK
FROM openjdk:21-rc-slim-bookworm

# Crie o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR para o diretório de trabalho no contêiner
COPY ./build/libs/mini-itau-bff-0.0.1-SNAPSHOT.jar /app/mini-itau.jar

# Exponha a porta 8080 para permitir o acesso ao seu aplicativo
EXPOSE 8080

# Comando para executar o aplicativo quando o contêiner for iniciado
CMD ["java", "-jar", "mini-itau.jar"]
