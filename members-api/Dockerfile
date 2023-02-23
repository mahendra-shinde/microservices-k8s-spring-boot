FROM openjdk:11-jre-slim-buster 
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/members-api-1.0.jar /app/members-api-1.0.jar
ENV PORT=8080
EXPOSE 8080
ENV DBSERVER=localhost
ENV DBPORT=33060
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/members-api-1.0.jar"]