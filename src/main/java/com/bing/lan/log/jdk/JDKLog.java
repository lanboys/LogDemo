package com.bing.lan.log.jdk;

import java.net.URL;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created by 蓝兵 on 2019/6/21.
 * <p>
 * 配置信息，看{@link LogManager#readConfiguration()}上面注释
 * <p>
 * 真正打印日志的入口 {@link Logger#log(LogRecord record)}
 *
 * 日志中显示的中文【信息】两个字就在 {@link sun.util.logging.resources.logging_zh_CN}
 */

public class JDKLog {

  public static void main(String[] args) {
    //配置方式一，还未做测试
    //System.setProperty("java.util.logging.config.class", "");

    //配置方式二
    // 可在命令行配置， 如tomcat中catalina.sh就是这么配置的
    // LOGGING_CONFIG="-Djava.util.logging.config.file=$CATALINA_BASE/conf/logging.properties"
    // -Djava.util.logging.config.file=/usr/local/service/tomcat/conf/logging.properties

    URL logging = JDKLog.class.getResource("/jdk-logging.properties");
    System.setProperty("java.util.logging.config.file", logging.getPath());

    Logger logger = Logger.getLogger("lan.bing");
    logger.info("Hello World.");
    logger.info("----info----");
    logger.config("----config----");
    logger.fine("----fine----");
  }
}
