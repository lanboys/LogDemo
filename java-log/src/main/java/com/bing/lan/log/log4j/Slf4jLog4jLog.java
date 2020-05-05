package com.bing.lan.log.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lb on 2020/5/5.
 */
public class Slf4jLog4jLog {

    public static void main(String args[]) {
        Logger logger = LoggerFactory.getLogger("lan.bing");
        logger.debug("Debug Level");
        logger.info("Info Level");
        logger.warn("Warn Level");
        logger.error("Error Level");
    }
}
