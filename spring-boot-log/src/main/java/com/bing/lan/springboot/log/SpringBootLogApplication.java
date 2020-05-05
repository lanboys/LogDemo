package com.bing.lan.springboot.log;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;

@RestController
@SpringBootApplication
public class SpringBootLogApplication {

    public static void main(String[] args) {
        //打印 Logback 内部状态, 有了logback.xml文件会自动打印
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);

        SpringApplication.run(SpringBootLogApplication.class, args);
    }

    @RequestMapping
    public String hello() {
        return "hello spring-boot logger";
    }
}
