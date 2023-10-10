package org.zhumagulova.socialmediaapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableJpaRepositories(basePackages = "org.zhumagulova.chatgptlow.dao")
@SpringBootApplication
@EnableCaching
public class ChatGptLowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatGptLowApplication.class, args);
    }

    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
