FROM openjdk:17
EXPOSE 8080
ADD target/pmilabs-0.0.1-SNAPSHOT.jar pmilabs-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pmilabs-0.0.1-SNAPSHOT.jar"]