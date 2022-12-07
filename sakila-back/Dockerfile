FROM eclipse-temurin:11.0.17_8-jre-alpine
EXPOSE 8080
ADD target/sakila-back.jar sakila-back.jar
ENTRYPOINT ["java","-jar","/sakila-back.jar"]
