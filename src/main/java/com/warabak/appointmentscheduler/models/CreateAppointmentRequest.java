package com.warabak.appointmentscheduler.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class CreateAppointmentRequest {

    @NotNull
    @Future
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public final ZonedDateTime scheduledDate;

    @NotNull
    @Positive
    public final Integer durationInMinutes;

    @NotBlank
    public final String doctorFullName;

    @Pattern(regexp = "Available|Booked")
    public final String status;

    @NotNull
    @NumberFormat(style = Style.CURRENCY)
    public final String price;

    @JsonCreator
    public CreateAppointmentRequest(
        @JsonProperty final String scheduledDate,
        @JsonProperty final Integer durationInMinutes,
        @JsonProperty final String doctorFullName,
        @JsonProperty final String status,
        @JsonProperty final String price
    ) {
        this.scheduledDate = ZonedDateTime.parse(scheduledDate);
        this.durationInMinutes = durationInMinutes;
        this.doctorFullName = doctorFullName;
        this.status = status;
        this.price = price;
    }
}