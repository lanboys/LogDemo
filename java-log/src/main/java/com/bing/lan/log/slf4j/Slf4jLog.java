package com.bing.lan.log.slf4j;

import com.bing.lan.log.slf4j.cons.ConsoleLog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 蓝兵 on 2019/6/21.
 */

public class Slf4jLog {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Slf4jLog.class);
        Logger rootLogger = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        System.out.println("main(): -------------------------------------");

        //logger.trace("Trace Level.");
        //logger.debug("Debug Level.");
        //logger.info("Info Level.");
        //logger.warn("Warn Level.");
        logger.error("Error Level.");

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new ConsoleLog();
            new FileLog();
        }
    }
}