package com.warabak.appointmentscheduler.controllers;

import com.warabak.appointmentscheduler.models.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public Status getStatus() {
        return Status.green();
    }

}
