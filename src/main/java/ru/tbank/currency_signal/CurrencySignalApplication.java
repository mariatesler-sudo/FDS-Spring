package ru.tbank.currency_signal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CurrencySignalApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencySignalApplication.class, args);
    }
}