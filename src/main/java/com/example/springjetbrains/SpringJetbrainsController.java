package com.example.springjetbrains;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringJetbrainsController {
    @RequestMapping("/")
    public String helloWorld(){
        return "Hello World from Spring Boot";
    }
}
