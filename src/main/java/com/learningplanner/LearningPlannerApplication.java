package com.learningplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LearningPlannerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearningPlannerApplication.class, args);
    }
}
