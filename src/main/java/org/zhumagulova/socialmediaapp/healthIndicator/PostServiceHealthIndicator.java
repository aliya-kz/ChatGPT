package org.zhumagulova.socialmediaapp.healthIndicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.zhumagulova.socialmediaapp.dao.PostRepository;
import org.zhumagulova.socialmediaapp.dao.UserRepository;

@Component
public class PostServiceHealthIndicator implements HealthIndicator {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServiceHealthIndicator(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Health health() {
        if (postRepository != null && userRepository != null) {
            return Health.up().build();
        } else {
            return Health.down().withDetail("error", "No dependencies found").build();
        }
    }
}