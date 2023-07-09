FROM openjdk:17
VOLUME /tmp
COPY target/*.jar myapplication
ENTRYPOINT ["java", "-jar","myapplication"]
EXPOSE 8081