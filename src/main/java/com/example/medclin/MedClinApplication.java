package com.example.medclin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class MedClinApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedClinApplication.class, args);
    }

}
