package com.warabak.appointmentscheduler.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

    private final String status;

    public static Status green() {
        return new Status("green");
    }

    @JsonCreator
    private Status(@JsonProperty final String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
