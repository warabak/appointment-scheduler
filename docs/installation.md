## Maven Build

We let Maven be responsible for building the Docker image for us, with the help of [Spotify's dockerfile-maven project](https://github.com/spotify/dockerfile-maven)
To make the app's Docker image, simply use maven :

```bash
mvn clean install
```

If all goes well, you will see the Dockerfile being built at the very end of the maven process

```dockerfile
[INFO] --- dockerfile-maven-plugin:1.4.10:build (default) @ appointment-scheduler ---
[INFO] dockerfile: null
[INFO] contextDirectory: /Users/nwarabak/projects/appointment-scheduler
[INFO] Building Docker context /Users/nwarabak/projects/appointment-scheduler
[INFO] Path(dockerfile): null
[INFO] Path(contextDirectory): /Users/nwarabak/projects/appointment-scheduler
[INFO]
[INFO] Image will be built as nwarabak/appointment-scheduler:1.0
[INFO]
[INFO] Step 1/6 : FROM adoptopenjdk/openjdk11:alpine-slim
[INFO]
[INFO] Pulling from adoptopenjdk/openjdk11
[INFO] Digest: sha256:081084595d04c751ab69ea070cc0c248caebac17b7ea40a4d13fb905771ef928
[INFO] Status: Image is up to date for adoptopenjdk/openjdk11:alpine-slim
[INFO]  ---> f9a74588273a
[INFO] Step 2/6 : MAINTAINER Nick Warabak <nick.warabak@gmail.com>
[INFO]
[INFO]  ---> Using cache
[INFO]  ---> a704e78565e0
[INFO] Step 3/6 : ARG JAR_FILE
[INFO]
[INFO]  ---> Using cache
[INFO]  ---> 68d6e9380819
[INFO] Step 4/6 : ADD target/${JAR_FILE} /usr/share/appointment-scheduler/appointment-scheduler.jar
[INFO]
[INFO]  ---> e694ce57ef2e
[INFO] Step 5/6 : EXPOSE 8080
[INFO]
[INFO]  ---> Running in a08067dd656b
[INFO] Removing intermediate container a08067dd656b
[INFO]  ---> 1e963c656375
[INFO] Step 6/6 : ENTRYPOINT ["java", "-jar", "/usr/share/appointment-scheduler/appointment-scheduler.jar"]
[INFO]
[INFO]  ---> Running in 75d550becf73
[INFO] Removing intermediate container 75d550becf73
[INFO]  ---> 50ad20e2388b
[INFO] Successfully built 50ad20e2388b
[INFO] Successfully tagged nwarabak/appointment-scheduler:1.0
[INFO]
[INFO] Detected build of image with id 50ad20e2388b
[INFO] Building jar: /Users/nwarabak/projects/appointment-scheduler/target/appointment-scheduler-1.0-docker-info.jar
[INFO] Successfully built nwarabak/appointment-scheduler:1.0
[INFO]
[INFO] --- maven-install-plugin:2.5.2:install (default-install) @ appointment-scheduler ---
[INFO] Installing /Users/nwarabak/projects/appointment-scheduler/target/appointment-scheduler-1.0.jar to /Users/nwarabak/.m2/repository/com/warabak/appointment-scheduler/1.0/appointment-scheduler-1.0.jar
[INFO] Installing /Users/nwarabak/projects/appointment-scheduler/pom.xml to /Users/nwarabak/.m2/repository/com/warabak/appointment-scheduler/1.0/appointment-scheduler-1.0.pom
[INFO] Installing /Users/nwarabak/projects/appointment-scheduler/target/appointment-scheduler-1.0-docker-info.jar to /Users/nwarabak/.m2/repository/com/warabak/appointment-scheduler/1.0/appointment-scheduler-1.0-docker-info.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

To run the freshly built image, using an embedded H2 database :

```bash
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=h2" nwarabak/appointment-scheduler:1.0
```

And verify you can hit the application's _/health_ endpoint

```bash
curl -S localhost:8080/health
```

```json
{"health":"green"}
```

## Docker Compose
After building with maven, you are now free to use `docker-compose` to stand up both the Spring Boot application and a Postgres instance.

To run, simply execute :

```bash
docker-compose up
```

Again, verify you can hit the application's _/health_ endpoint. That's it!