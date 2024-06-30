FROM eclipse-temurin:18

WORKDIR /app

COPY indeedclone-0.0.1-SNAPSHOT.jar /app/indeedclone-0.0.1-SNAPSHOT.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "indeedclone-0.0.1-SNAPSHOT.jar"]