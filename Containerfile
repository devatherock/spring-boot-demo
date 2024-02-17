FROM docker.io/eclipse-temurin:17.0.7_7-jre-alpine

COPY build/libs/spring-boot-demo*.jar demo.jar

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "demo.jar"]
