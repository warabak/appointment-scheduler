package com.warabak.appointmentscheduler.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

public class CreateAppointmentResponse {

    public final Long id;
    public final ZonedDateTime createdAt;
    public final ZonedDateTime scheduledDate;
    public final Integer durationInMinutes;
    public final String doctorFullName;
    public final String status;
    public final String price;

    @JsonCreator
    public CreateAppointmentResponse(
        @JsonProperty final Long id,
        @JsonProperty final ZonedDateTime createdAt,
        @JsonProperty final ZonedDateTime scheduledDate,
        @JsonProperty final Integer durationInMinutes,
        @JsonProperty final String doctorFullName,
        @JsonProperty final String status,
        @JsonProperty final String price
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.scheduledDate = scheduledDate;
        this.durationInMinutes = durationInMinutes;
        this.doctorFullName = doctorFullName;
        this.status = status;
        this.price = price;
    }
}
