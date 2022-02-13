package com.minholee93.margincallcalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MarginCallCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarginCallCalculatorApplication.class, args);
    }

}
