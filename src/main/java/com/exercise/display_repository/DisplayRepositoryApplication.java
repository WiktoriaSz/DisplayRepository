package com.exercise.display_repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DisplayRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisplayRepositoryApplication.class, args);

    }

    @Bean
    public RestTemplate rest() {
        return new RestTemplate();
    }

}
