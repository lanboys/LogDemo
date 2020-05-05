package com.bing.lan.log.log4j;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by lb on 2020/5/5.
 * <p>
 * https://www.cnblogs.com/baizhanshi/p/7911123.html
 * https://www.cnblogs.com/pekkle/p/6813458.html
 * https://www.jianshu.com/p/190c56429ec4
 * https://www.cnblogs.com/manayi/p/9570411.html
 * <p>
 * 不支持使用占位符，不利于代码阅读
 * <p>
 * 默认配置文件名
 * LogManager.DEFAULT_XML_CONFIGURATION_FILE
 */

public class Log4jLog {

    public static void main(String args[]) {
        Logger logger = LogManager.getLogger(Log4jLog.class);
        logger.debug("Debug Level");
        logger.info("Info Level");
        logger.warn("Warn Level");
        logger.error("Error Level");
        // [05 10:58:53,885 DEBUG] [main] log4j.Log4jLog - Debug Level
        // [05 10:58:53,886 INFO ] [main] log4j.Log4jLog - Info Level
        // [05 10:58:53,886 WARN ] [main] log4j.Log4jLog - Warn Level
        // [05 10:58:53,888 ERROR] [main] log4j.Log4jLog - Error Level
    }
}
