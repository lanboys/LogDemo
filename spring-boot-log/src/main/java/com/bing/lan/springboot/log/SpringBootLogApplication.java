package com.bing.lan.springboot.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在 {@link LoggingApplicationListener#onApplicationStartingEvent(ApplicationStartingEvent)}
 * {@link LoggingSystem#get(ClassLoader, String)}
 * 中确定使用什么日志框架
 */
@RestController
@SpringBootApplication
public class SpringBootLogApplication {

    private static Logger rootLogger = LoggerFactory.getLogger("ROOT");
    private static Logger slf4jLogger = LoggerFactory.getLogger(SpringBootLogApplication.class);
    private static java.util.logging.Logger julLogger = java.util.logging.Logger.getLogger(SpringBootLogApplication.class.getName());

    public static void main(String[] args) {
        //打印 Logback 内部状态, 有了logback.xml文件会自动打印
        //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //StatusPrinter.print(lc);

        SpringApplication.run(SpringBootLogApplication.class, args);
    }

    @RequestMapping
    public String hello() {
        rootLogger.info(" >>>>> rootLogger >>>>> hello spring-boot logger");
        slf4jLogger.info(" >>>>> slf4jLogger >>>>> hello spring-boot logger");
        julLogger.info(" >>>>> julLogger >>>>> hello spring-boot logger");
        return "hello spring-boot logger";
    }
}
