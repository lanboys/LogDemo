package com.bing.lan.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 蓝兵 on 2019/6/22.
 */

public class ConsoleLog {

    public ConsoleLog() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.warn("Console Log Test");
    }
}
