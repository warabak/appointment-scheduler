package com.warabak.appointmentscheduler;

import com.warabak.appointmentscheduler.controllers.StatusController;
import com.warabak.appointmentscheduler.models.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Profile("local")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = LocalApplication.class)
@ExtendWith(SpringExtension.class)
class ControllerTests {

    @Autowired
    private StatusController statusController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void canLoadSpringContext() {
        Assertions.assertNotNull(this.statusController);
    }

    @Test
    void canQueryStatusEndpoint() {
        // Arbitrary test to make sure we can stand up and successfully query a RESTful endpoint in a test
        final String url = String.format("http://localhost:%s/status", this.port);
        final Status response = this.restTemplate.getForObject(url, Status.class);
        Assertions.assertEquals("green", response.getStatus());
    }
}