package com.warabak.appointmentscheduler;

import com.warabak.appointmentscheduler.controllers.HealthController;
import com.warabak.appointmentscheduler.models.Health;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = Application.class)
@ExtendWith(SpringExtension.class)
class HealthControllerTests {

    @Autowired
    private HealthController healthController;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void canLoadSpringContext() {
        Assertions.assertNotNull(this.healthController);
    }

    @Test
    void canQueryHealthEndpoint() {
        // Arbitrary test to make sure we can stand up and successfully query a RESTful endpoint in a test
        final String url = String.format("http://localhost:%s/health", this.port);
        final Health response = this.restTemplate.getForObject(url, Health.class);
        Assertions.assertEquals("green", response.getHealth());
    }
}