package com.example.agregio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.agregio.infrastructure.repository")
public class AgregioApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgregioApplication.class, args);
    }

}
