package com.bing.lan.log.slf4j;

import com.bing.lan.log.slf4j.cons.ConsoleLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 蓝兵 on 2019/6/21.
 */

public class Slf4jLog {

    //private final static Logger logger = LoggerFactory.getLogger(Slf4jLog.class);
    //private final static Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Slf4jLog.class);
        Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

        //logger.trace("Trace Level.");
        //logger.debug("Debug Level.");
        //logger.info("Info Level.");
        //logger.warn("Warn Level.");
        logger.error("Error Level.");

        new ConsoleLog();
        new FileLog();

        //打印 Logback 内部状态
        //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        //StatusPrinter.print(lc);
    }
}