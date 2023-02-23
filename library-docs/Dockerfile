FROM openjdk:11-jre-slim-buster 
COPY target/library-docs-1.0.jar /app/library-docs-1.0.jar
ENV PORT=8080
EXPOSE 8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/library-docs-1.0.jar"]