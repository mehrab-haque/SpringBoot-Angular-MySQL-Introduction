package com.example.jdk17test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class Jdk17testApplication {

    public static void main(String[] args) {
        SpringApplication.run(Jdk17testApplication.class, args);
    }

}
