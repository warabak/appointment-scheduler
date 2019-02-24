package com.warabak.appointmentscheduler.repos;

import com.warabak.appointmentscheduler.entities.Appointment;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.limit-query-result
    List<Appointment> findByScheduledDateBetweenOrderByPriceDesc(final ZonedDateTime start, final ZonedDateTime scheduledDate);

}