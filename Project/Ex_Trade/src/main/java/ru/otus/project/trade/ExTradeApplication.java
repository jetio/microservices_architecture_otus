package ru.otus.project.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ExTradeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExTradeApplication.class, args);
    }
}