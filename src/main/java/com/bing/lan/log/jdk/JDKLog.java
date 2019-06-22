package com.bing.lan.log.jdk;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Logger;

/**
 * Created by 蓝兵 on 2019/6/21.
 */

public class JDKLog {

    public static void main(String[] args) {
        // jul-to-slf4j 桥接代码
        //SLF4JBridgeHandler.removeHandlersForRootLogger();
        //SLF4JBridgeHandler.install();

        Logger logger = Logger.getLogger("lan.bing");
        logger.info("Hello World.");
        logger.info("----1----");
        logger.info("----2----");
    }
}
