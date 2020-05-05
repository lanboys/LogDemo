package com.bing.lan.springboot.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLogApplication.class, args);
    }

    @RequestMapping
    public String hello() {
        return "hello spring-boot logger";
    }
}
