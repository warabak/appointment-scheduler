package com.warabak.appointmentscheduler.controllers;

import com.warabak.appointmentscheduler.models.CreateAppointmentRequest;
import com.warabak.appointmentscheduler.models.CreateAppointmentResponse;
import com.warabak.appointmentscheduler.repos.AppointmentRepository;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping
    public CreateAppointmentResponse create(@Valid @RequestBody CreateAppointmentRequest request) {
        final ZonedDateTime now = Instant.now().atZone(ZoneOffset.UTC);
        return new CreateAppointmentResponse(1L, now, request.scheduledDate, request.durationInMinutes, request.doctorFullName, request.status, request.price);
    }
}
