package com.bing.lan.log.jdk;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.util.logging.Logger;

/**
 * Created by 蓝兵 on 2019/6/21.
 * <p>
 * jul-to-slf4j
 * <p>
 * 桥接方式1：添加代码
 * SLF4JBridgeHandler.removeHandlersForRootLogger();
 * SLF4JBridgeHandler.install();
 * <p>
 * 桥接方式2：在配置文件logging.properties配置
 * 添加启动参数: -Djava.util.logging.config.file=E:\IDEA_workspace\LogDemo\src\main\resources\logging.properties
 *
 * https://www.slf4j.org/api/org/slf4j/bridge/SLF4JBridgeHandler.html
 */

public class JDKLog {

    public static void main(String[] args) {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();

        Logger logger = Logger.getLogger("lan.bing");
        logger.info("Hello World.");
        logger.info("----1----");
        logger.info("----2----");

        //五月 05, 2020 1:58:28 下午 com.bing.lan.log.jdk.JDKLog main
        //信息: Hello World.
        //五月 05, 2020 1:58:28 下午 com.bing.lan.log.jdk.JDKLog main
        //信息: ----1----
        //五月 05, 2020 1:58:28 下午 com.bing.lan.log.jdk.JDKLog main
        //信息: ----2----
    }
}
