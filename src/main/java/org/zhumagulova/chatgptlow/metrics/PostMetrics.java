package org.zhumagulova.chatgptlow.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PostMetrics {

    private Counter postRequestCounter;

    public PostMetrics(MeterRegistry registry) {
        this.postRequestCounter = Counter.builder("posts.request.count")
                .description("Counts number of requests to uri /posts/authorId")
                .register(registry);
    }

    public void increment() {
        postRequestCounter.increment();
    }
}
