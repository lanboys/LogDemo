//package com.bing.lan.log.log4j;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//
///**
// * Created by 蓝兵 on 2019/6/21.
// *
// * <p>
// * 不支持使用占位符，不利于代码阅读
// */
//
//public class Log4jLog {
//
//    public static void main(String args[]) {
//        Logger logger = LogManager.getLogger(Log4jLog.class);
//        logger.debug("Debug Level");
//        logger.info("Info Level");
//        logger.warn("Warn Level");
//        logger.error("Error Level");
//        //21:14:33.526 [main] INFO  com.bing.lan.log.log4j.Log4j2Log - Info Level
//        //21:14:33.528 [main] WARN  com.bing.lan.log.log4j.Log4j2Log - Warn Level
//        //21:14:33.528 [main] ERROR com.bing.lan.log.log4j.Log4j2Log - Error Level
//    }
//}
