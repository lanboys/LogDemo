package com.bing.lan.log.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lb on 2020/5/5.
 * logback 默认以 slf4j 作为日志门面，即 作为 slf4j 的实现
 */
public class LogbackLog {

    public static void main(String[] args) {
        //打印 Logback 内部状态, 有了logback.xml文件会自动打印
        //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //StatusPrinter.print(lc);

        Logger logger = LoggerFactory.getLogger(LogbackLog.class);
        logger.trace("Trace Level.");
        logger.debug("Debug Level.");
        logger.info("Info Level.");
        logger.warn("Warn Level.");
        logger.error("Error Level.");

        logger = LoggerFactory.getLogger(Integer.class);
        logger.trace("Trace Level.");
        logger.debug("Debug Level.");
        logger.info("Info Level.");
        logger.warn("Warn Level.");
        logger.error("Error Level.");
    }
}
