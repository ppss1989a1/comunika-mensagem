FROM openjdk:11
ADD target/comunika-mensagem-0.0.1-SNAPSHOT.jar comunika-mensagem-0.0.1-SNAPSHOT.jar
EXPOSE 8086
ENTRYPOINT ["java","-Dspring.profiles.active=devtst", "-jar", "comunika-mensagem-0.0.1-SNAPSHOT.jar"]