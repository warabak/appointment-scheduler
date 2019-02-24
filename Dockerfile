FROM adoptopenjdk/openjdk11:alpine-slim
MAINTAINER Nick Warabak <nick.warabak@gmail.com>

# Via https://spring.io/guides/gs/spring-boot-docker/
#VOLUME /tmp
#
#ARG DEPENDENCY=target/dependency
#
#COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY ${DEPENDENCY}/META-INF /app/META-INF
#COPY ${DEPENDENCY}/BOOT-INF/classes /app
#
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.warabak.appointmentscheduler.RemoteApplication"]

# The parameterized name of the built jar
ARG JAR_FILE

# Add the Maven dependencies separately, to take advantage of layer caching
# ADD target/lib /usr/share/appointment-scheduler/lib

# Add the jar itself
ADD target/${JAR_FILE} /usr/share/appointment-scheduler/appointment-scheduler.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/usr/share/appointment-scheduler/appointment-scheduler.jar"]
