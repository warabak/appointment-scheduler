package com.warabak.appointmentscheduler.services;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Profile("random-scheduler")
@Service
public class RandomScheduler {

    /**
     * What a weird class. Here's the objective : create appointments at a random interval.
     *
     * Two things : 1) We don't _always_ want to enable this for obvious reasons. Limit it to a specific Spring profile and allow it to be included, if desired.
     * 2) We're opting to do this in the application itself to keep everything centralized. This can just as well be a bash script that runs outside the app.
     *
     * Due to #2, we want to lean on Spring where possible. This means trying to reuse existing concepts - namely @Scheduled. Spring doesn't include a
     *
     * @ Scheduled(fixedDelay = randomEveryTime) for obvious reasons, so this will have to work slightly differently. Schedule this method to run every second
     * and give the method a chance at doing something useful. Think : every second has a n% chance at creating a new appointment, where n is configurable via
     * Spring.
     *
     * Do not use this in a real world setting :)
     */

    private final Logger logger = LoggerFactory.getLogger(RandomScheduler.class);

    @Autowired
    @Value("#{new java.util.Random()}")
    private Random random;

    @Value("${random-scheduler.chanceToCreate:5}")
    private int chanceToCreate;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentFaker appointmentFaker;

    @Scheduled(fixedDelay = 1000)
    public void maybeCreateNewAppointment() {
        final int roll = random.nextInt(100) + 1;

        if (roll < chanceToCreate) {
            logger.info("Creating new random appointment");
            appointmentService.create(appointmentFaker.create());
        }
    }
}
