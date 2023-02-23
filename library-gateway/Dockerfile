FROM openjdk:11-jre-slim-buster 
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY target/library-gateway-1.0.jar /app/library-gateway-1.0.jar
ENV PORT=8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/library-gateway-1.0.jar"]