FROM adoptopenjdk/openjdk11-openj9:jre-11.0.8_10_openj9-0.21.0-alpine
COPY build/libs/spring-boot-demo*.jar demo.jar
EXPOSE 8080
CMD java -jar demo.jar
