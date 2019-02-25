[![Travis Build Status](https://travis-ci.com/warabak/appointment-scheduler.svg?branch=master)](https://travis-ci.com/warabak/appointment-scheduler)

A demo project for showcasing how to use Spring Boot to implement RESTful web services by creating a small appointment scheduler application.

# Technologies
- Java 11
- JUnit 5
- Spring Boot 2.1
- Postgres 10
- Docker 18.09
- Docker Compose 3.7

# Quickstart
## How to Build

#### Run the following from your command line
```bash
mvn clean install
docker-compose up --detach
```

#### Verify docker-compose starts - you should see the following
```bash
Starting appointment-scheduler_db_1 ... done
Recreating appointment-scheduler_api_1 ... done
```

#### Check docker for the running container information
```bash
docker ps
CONTAINER ID        IMAGE                                COMMAND                  CREATED             STATUS                    PORTS                    NAMES
2e440df7141b        nwarabak/appointment-scheduler:1.0   "java -jar /usr/shar…"   15 seconds ago      Up 13 seconds             0.0.0.0:8080->8080/tcp   appointment-scheduler_api_1
34860ef33560        postgres:10                          "docker-entrypoint.s…"   6 minutes ago       Up 14 seconds (healthy)   0.0.0.0:5432->5432/tcp   appointment-scheduler_db_1
```

Ensure the the application is alive and happy by calling the health API.

# APIs

## Health
### GET /health
Checks the health of the application

```bash
curl -s 'http://localhost:8080/health' | jq .
```

```json
{
  "health": "green"
}
```

## Appointments
### GET /appointments
Lists all existing appointments

```bash
curl -s 'http://localhost:8080/appointments' | jq .
```

```json
[
  {
    "id": 1,
    "createdAt": "2019-02-24T19:11:51.040728-05:00",
    "scheduledDate": "2019-05-05T00:08:43.487-04:00",
    "durationInMinutes": 90,
    "doctorFullName": "Benedict Hermann",
    "status": "Available",
    "price": 177.37
  },
  {
    "id": 2,
    "createdAt": "2019-02-24T19:11:52.911455-05:00",
    "scheduledDate": "2019-08-05T23:51:16.157-04:00",
    "durationInMinutes": 50,
    "doctorFullName": "Miss Gwyneth Hegmann",
    "status": "Available",
    "price": 859.68
  },
  {
    "id": 3,
    "createdAt": "2019-02-24T19:11:53.651012-05:00",
    "scheduledDate": "2019-03-09T04:04:42.276-05:00",
    "durationInMinutes": 85,
    "doctorFullName": "Dia Parisian",
    "status": "Booked",
    "price": 684.83
  },
  {
    "id": 4,
    "createdAt": "2019-02-24T19:11:54.306298-05:00",
    "scheduledDate": "2019-12-13T15:39:28.278-05:00",
    "durationInMinutes": 130,
    "doctorFullName": "Rhett Denesik Sr.",
    "status": "Booked",
    "price": 479.36
  },
  {
    "id": 5,
    "createdAt": "2019-02-24T19:11:54.946156-05:00",
    "scheduledDate": "2020-02-11T19:11:40.226-05:00",
    "durationInMinutes": 60,
    "doctorFullName": "Glynda Predovic",
    "status": "Booked",
    "price": 398.74
  }
]
```

### GET /appointments?start={date}&end={date}
Lists all existing appointments between the given start and end dates.
Both dates must be provided and must be an ISO-8601 date time format

```bash
curl -s 'http://localhost:8080/appointments?start=2019-01-01T00:00:00.000-00:00&end=2019-06-01T00:00:00.000-00:00' | jq .
```

```json
[
  {
    "id": 3,
    "createdAt": "2019-02-24T19:11:53.651012-05:00",
    "scheduledDate": "2019-03-09T04:04:42.276-05:00",
    "durationInMinutes": 85,
    "doctorFullName": "Dia Parisian",
    "status": "Booked",
    "price": 684.83
  },
  {
    "id": 1,
    "createdAt": "2019-02-24T19:11:51.040728-05:00",
    "scheduledDate": "2019-05-05T00:08:43.487-04:00",
    "durationInMinutes": 90,
    "doctorFullName": "Benedict Hermann",
    "status": "Available",
    "price": 177.37
  }
]
```

### GET /appointments/{id}
Find an appointment by its ID.

```bash
curl -s 'http://localhost:8080/appointments/3' | jq .
```

```json
{
  "id": 3,
  "createdAt": "2019-02-24T19:11:53.651012-05:00",
  "scheduledDate": "2019-03-09T04:04:42.276-05:00",
  "durationInMinutes": 85,
  "doctorFullName": "Dia Parisian",
  "status": "Booked",
  "price": 684.83
}
```

### PUT /appointments/{id}/{status}
Update an appointment's status

```bash
curl -s -X PUT 'http://localhost:8080/appointments/3/Available' | jq .
```

```json
{
  "id": 3,
  "createdAt": "2019-02-24T19:11:53.651012-05:00",
  "scheduledDate": "2019-03-09T04:04:42.276-05:00",
  "durationInMinutes": 85,
  "doctorFullName": "Dia Parisian",
  "status": "Available",
  "price": 684.83
}
```

### POST /appointments
Create a new appointment.

```bash
curl -s -d '{"scheduledDate":"2019-03-01T12:30:00+00:00","durationInMinutes":"60","doctorFullName":"Shelby Jahns","status":"Available","price":100}' -H 'Content-Type:application/json' 'http://localhost:8080/appointments' | jq .
```

```json
{
  "id": 9,
  "createdAt": "2019-02-25T01:09:15.487552Z",
  "scheduledDate": "2019-03-01T12:30:00Z",
  "durationInMinutes": 60,
  "doctorFullName": "Shelby Jahns",
  "status": "Available",
  "price": 100
}
```

### DELETE /appointments/{id}
Delete an appointment by its ID.

```bash
curl -s -X DELETE 'http://localhost:8080/appointments/9' | jq .
```

```json
{
  "id": 9,
  "createdAt": "2019-02-24T20:09:15.487552-05:00",
  "scheduledDate": "2019-03-01T07:30:00-05:00",
  "durationInMinutes": 60,
  "doctorFullName": "Shelby Jahns",
  "status": "Available",
  "price": 100
}
```

## Randomizer
### POST /randomizer
Create a randomly-generated experiment.

```bash
curl -s -X POST http://localhost:8080/randomizer | jq .
```

```json
{
  "id": 10,
  "createdAt": "2019-02-25T01:12:10.521149Z",
  "scheduledDate": "2019-10-10T04:18:15.458Z",
  "durationInMinutes": 110,
  "doctorFullName": "Santiago Klocko",
  "status": "Available",
  "price": 675.11
}
```

## Further reading
[Project next steps](docs/next-steps.md)
[Full installation instructions](docs/installation.md)