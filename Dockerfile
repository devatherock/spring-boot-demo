FROM eclipse-temurin:17.0.7_7-jre-alpine
COPY build/libs/spring-boot-demo*.jar demo.jar
EXPOSE 8080
CMD java -jar demo.jar
