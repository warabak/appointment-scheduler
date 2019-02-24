package com.warabak.appointmentscheduler.services;

import com.warabak.appointmentscheduler.entities.Appointment;
import com.warabak.appointmentscheduler.entities.Status;
import com.warabak.appointmentscheduler.models.CreateAppointmentRequest;
import com.warabak.appointmentscheduler.models.CreateAppointmentResponse;
import com.warabak.appointmentscheduler.repos.AppointmentRepository;
import com.warabak.appointmentscheduler.repos.StatusRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private StatusRepository statusRepository;

    public CreateAppointmentResponse create(final CreateAppointmentRequest request) {

        // Find the requested status - if it doesn't exist, we will not move forward.
        final Status status = statusRepository
            .findOne(Example.of(new Status(request.status)))
            .orElseThrow(() -> new IllegalArgumentException(String.format("Unknown status %s", request.status)));

        final Appointment appointment = new Appointment(
            request.scheduledDate,
            request.durationInMinutes,
            request.doctorFullName,
            status,
            request.price
        );

        // Persist to the database
        final Appointment saved = appointmentRepository.save(appointment);

        return new CreateAppointmentResponse(
            saved.getId(),
            saved.getCreatedAt(),
            saved.getScheduledDate(),
            saved.getDurationInMinutes(),
            saved.getDoctorFullName(),
            saved.getStatus().getStatus(),
            saved.getPrice()
        );
    }

}
