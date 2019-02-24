package com.warabak.appointmentscheduler.entities;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @Column(name = "appointment_date")
    private ZonedDateTime scheduledDate;

    @Column(name = "appointment_duration")
    private Integer durationInMinutes;

    @Column(name = "name_of_doctor")
    private String doctorFullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "price")
    private String price;

    // JPA requires a default constructor - we'll need to keep the getters / setters to satisfy it
    public Appointment() {
    }

    // Generally, prefer using a constructor where possible so we don't accidentally miss invoking a requisite setter
    public Appointment(
        final ZonedDateTime scheduledDate,
        final Integer durationInMinutes,
        final String doctorFullName,
        final Status status,
        final String price
    ) {
        this.createdAt = Instant.now().atZone(ZoneOffset.UTC);
        this.scheduledDate = scheduledDate;
        this.durationInMinutes = durationInMinutes;
        this.doctorFullName = doctorFullName;
        this.status = status;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(final ZonedDateTime scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(final Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(final String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(final String price) {
        this.price = price;
    }
}
