package org.zhumagulova.socialmediaapp.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class HealthMetrics {

    private Counter healthRequestCounter;

    public HealthMetrics(MeterRegistry registry) {
        this.healthRequestCounter = Counter.builder("health.request.count")
                .description("Counts number of requests to uri /actuator/health")
                .register(registry);
    }

    public void increment() {
        healthRequestCounter.increment();
    }
}
