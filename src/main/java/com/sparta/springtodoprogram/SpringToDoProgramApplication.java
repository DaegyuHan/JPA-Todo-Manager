package com.sparta.springtodoprogram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringToDoProgramApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringToDoProgramApplication.class, args);
    }

}
