package org.zhumagulova.chatgptlow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhumagulova.chatgptlow.metrics.HealthMetrics;

@RestController
@RequestMapping("/actuator/health")
public class HealthController {

    private final HealthMetrics healthMetrics;


    public HealthController(HealthMetrics healthMetrics) {
        this.healthMetrics = healthMetrics;
    }

    @GetMapping()
    public void get() {
        healthMetrics.increment();
    }
}
