package com.warabak.appointmentscheduler;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LocalApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .profiles("local")
            .bannerMode(Banner.Mode.OFF)
            .sources(LocalApplication.class)
            .run(args);
    }
}