package com.warabak.appointmentscheduler;

import com.warabak.appointmentscheduler.controllers.AppointmentController;
import com.warabak.appointmentscheduler.models.CreateAppointmentRequest;
import com.warabak.appointmentscheduler.models.CreateAppointmentResponse;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Profile("local")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LocalApplication.class)
@ExtendWith(SpringExtension.class)
class AppointmentControllerTests {

    @Autowired
    private AppointmentController appointmentController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void canLoadSpringContext() {
        Assertions.assertNotNull(this.appointmentController);
    }

    @Test
    void canCreateNewAppointment() {
        final ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
        final ZonedDateTime oneDayFromNow = now.plus(1, ChronoUnit.DAYS);

        final CreateAppointmentRequest request = new CreateAppointmentRequest(oneDayFromNow, 60, "Dr. Shelby Jahns", "Available", "100.01");
        final ResponseEntity<CreateAppointmentResponse> httpResponse = this.restTemplate.postForEntity(getUrl(), request, CreateAppointmentResponse.class);

        Assertions.assertTrue(httpResponse.getStatusCode().is2xxSuccessful(), String.valueOf(httpResponse.getStatusCodeValue()));

        final CreateAppointmentResponse response = httpResponse.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.id);
        Assertions.assertTrue(now.isBefore(response.createdAt));
        Assertions.assertTrue(request.scheduledDate.isEqual(response.scheduledDate));
        Assertions.assertEquals(request.durationInMinutes, response.durationInMinutes);
        Assertions.assertEquals(request.doctorFullName, response.doctorFullName);
        Assertions.assertEquals(request.price, response.price);
        Assertions.assertEquals(request.status, response.status);
    }

    private String getUrl() {
        return String.format("http://localhost:%s/appointment", this.port);
    }
}