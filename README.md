[![Travis Build Status](https://travis-ci.com/warabak/appointment-scheduler.svg?branch=master)](https://travis-ci.com/warabak/appointment-scheduler)
[![Coverage Status](https://coveralls.io/repos/github/warabak/appointment-scheduler/badge.svg)](https://coveralls.io/github/warabak/appointment-scheduler)

RESTful web services for scheduling appointments

# Technologies
- Java 11
- JUnit 5
- Spring Boot 2.1
- Postgres 10
- Docker 18.09
- Docker Compose 3.7

# Quickstart
## How to Build

### Run the following from your command line
```bash
mvn clean install
docker-compose up --detach
```

### Verify docker-compose starts - you should see the following
```bash
Starting appointment-scheduler_db_1 ... done
Recreating appointment-scheduler_api_1 ... done
```

### Check docker for the running container information
```bash
docker ps
CONTAINER ID        IMAGE                                COMMAND                  CREATED             STATUS                    PORTS                    NAMES
2e440df7141b        nwarabak/appointment-scheduler:1.0   "java -jar /usr/shar…"   15 seconds ago      Up 13 seconds             0.0.0.0:8080->8080/tcp   appointment-scheduler_api_1
34860ef33560        postgres:10                          "docker-entrypoint.s…"   6 minutes ago       Up 14 seconds (healthy)   0.0.0.0:5432->5432/tcp   appointment-scheduler_db_1
```

### Verify you can query the API's health check
```bash
curl -S localhost:8080/health
```

```json
{"health":"green"}
```

## How to Use
TODO

## Further reading
[Full installation instructions](docs/installation.md)