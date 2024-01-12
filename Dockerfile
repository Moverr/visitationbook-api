FROM openjdk:17

WORKDIR /app

#//todo: this is the out folder for the file
#COPY build/libs/auth-v1.0.0-alpha.jar app.jar

EXPOSE 8083

CMD ["java", "-jar", "/app/app.jar"]