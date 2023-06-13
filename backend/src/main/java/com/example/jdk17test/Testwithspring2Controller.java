package com.example.jdk17test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Testwithspring2Controller {
    @RequestMapping("/")
    public String hellow(){
        return "hi from spring about pageeeeeeeeee riyocafe\n";
    }
}
