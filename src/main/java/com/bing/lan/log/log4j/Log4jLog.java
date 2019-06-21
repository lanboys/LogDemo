package com.bing.lan.log.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by 蓝兵 on 2019/6/21.
 * <p>
 * https://www.cnblogs.com/baizhanshi/p/7911123.html
 * <p>
 * 不支持使用占位符，不利于代码阅读
 */

public class Log4jLog {

    public static void main(String args[]) {
        Logger logger = LogManager.getLogger(Log4jLog.class);
        logger.debug("Debug Level");
        logger.info("Info Level");
        logger.warn("Warn Level");
        logger.error("Error Level");
    }
}
