package com.learningplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "Smart Learning Scheduler Backend is Running 🚀";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}

