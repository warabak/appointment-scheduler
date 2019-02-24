package com.warabak.appointmentscheduler.controllers;

import com.warabak.appointmentscheduler.models.CreateAppointmentRequest;
import com.warabak.appointmentscheduler.models.AppointmentResponse;
import com.warabak.appointmentscheduler.services.AppointmentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public AppointmentResponse create(@Valid @RequestBody final CreateAppointmentRequest request) {
        return appointmentService.create(request);
    }

    @GetMapping
    public List<AppointmentResponse> list() {
        return appointmentService.list();
    }

    @GetMapping("/{id}")
    public AppointmentResponse find(@PathVariable final Long id) {
        return appointmentService.find(id);
    }

    @PutMapping("/{id}/{status}")
    public AppointmentResponse update(@PathVariable final Long id, @PathVariable final String status) {
        return appointmentService.update(id, status);
    }
}
