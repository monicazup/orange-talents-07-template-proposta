FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
# O arquivo jar da nossa aplicação que está localmente na pasta target da raiz do projeto

ADD ${JAR_FILE} application.jar
#ADICIONA o '*-all.jar' (local) para a pasta 'application.jar' no docker

ENV APP_NAME keymanager-rest
ENTRYPOINT ["java", "-jar", "/application.jar"]
#Sequência de comandos JAVA (java -jar /application.jar)
# manda rodar o java, depois rodar o jar em sua localização