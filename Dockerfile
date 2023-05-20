FROM openjdk:11-jdk
COPY target/*.jar USAINUA_RestAPI.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "USAINUA_RestAPI.jar"]