package com.bing.lan.log.slf4j.cons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 蓝兵 on 2019/6/22.
 */

public class ConsoleLog {

    public ConsoleLog() {
        Logger logger = LoggerFactory.getLogger(this.getClass());

        logger.trace("Console trace Log Test");
        logger.debug("Console debug Log Test");
        logger.info("Console info Log Test");
        logger.warn("Console warn Log Test");
        logger.error("Console error Log Test");
    }
}
