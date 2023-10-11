package org.zhumagulova.socialmediaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.zhumagulova.socialmediaapp.dao")
@SpringBootApplication
@EnableCaching
public class ChatGptLowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGptLowApplication.class, args);
    }

}
