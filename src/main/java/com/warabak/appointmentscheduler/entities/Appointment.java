package com.warabak.appointmentscheduler.entities;

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
import javax.validation.constraints.Digits;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "created_at", updatable = false, nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "appointment_date", nullable = false)
    private ZonedDateTime scheduledDate;

    @Column(name = "appointment_duration")
    private Integer durationInMinutes;

    @Column(name = "name_of_doctor")
    private String doctorFullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    // We will opt to only store in USD, forcing a precision down to the penny.
    // If we want to accept alternative currencies (even crypto currencies, where the precision might be far beyond two decimal places), prefer the use of outer
    // services to convert to a canonical representation (USD) before storing in the database.
    @Digits(integer = 1_000_000_000, fraction = 2)
    @Column(name = "price", nullable = false)
    private String price;

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

    public void setDuration(final Integer durationInMinutes) {
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
