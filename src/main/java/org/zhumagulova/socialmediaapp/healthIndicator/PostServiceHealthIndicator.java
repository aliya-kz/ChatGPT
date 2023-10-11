package org.zhumagulova.socialmediaapp.healthIndicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.zhumagulova.socialmediaapp.model.User;
import org.zhumagulova.socialmediaapp.service.PostService;

import java.util.List;

@Component
public class PostServiceHealthIndicator implements HealthIndicator {

    private final static long ID = 1L;
    private final PostService postService;

    public PostServiceHealthIndicator(PostService postService) {
        this.postService = postService;
    }

    @Override
    public Health health() {
        User user = new User(ID);
        if (postService.getPostsByAuthor(user) instanceof List) {
            return Health.up().build();
        } else {
            return Health.down().withDetail("error", "Health check not passed").build();
        }
    }
}