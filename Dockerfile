FROM openjdk:17-alpine

# Set the working directory
WORKDIR /app

COPY target/lmph-be-exam-3.2.0-SNAPSHOT.jar /app/lmph-be-exam.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/lmph-be-exam.jar"]