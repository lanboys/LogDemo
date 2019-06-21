package com.bing.lan.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 蓝兵 on 2019/6/21.
 */

public class Slf4jLog {

    private final static Logger logger = LoggerFactory.getLogger(Slf4jLog.class);

    public static void main(String[] args) {

        logger.trace("Trace Level.");
        logger.debug("Debug Level.");
        logger.info("Info Level.");
        logger.warn("Warn Level.");
        logger.error("Error Level.");
    }
}