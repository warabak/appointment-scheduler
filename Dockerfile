FROM adoptopenjdk/openjdk11:alpine-slim
MAINTAINER Nick Warabak <nick.warabak@gmail.com>

# The parameterized name of the built jar
ARG JAR_FILE

# Add the (fat) jar itself
ADD target/${JAR_FILE} /usr/share/appointment-scheduler/appointment-scheduler.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/appointment-scheduler/appointment-scheduler.jar"]