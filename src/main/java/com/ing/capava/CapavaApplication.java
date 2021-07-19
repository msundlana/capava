package com.ing.capava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CapavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapavaApplication.class, args);
    }

}
