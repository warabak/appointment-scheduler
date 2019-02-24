package com.warabak.appointmentscheduler.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Health {

    private final String health;

    public static Health green() {
        return new Health("green");
    }

    @JsonCreator
    private Health(@JsonProperty final String health) {
        this.health = health;
    }

    public String getHealth() {
        return health;
    }
}
