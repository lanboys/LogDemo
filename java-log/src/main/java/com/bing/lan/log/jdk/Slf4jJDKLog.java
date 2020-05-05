package com.bing.lan.log.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lb on 2020/5/5.
 */

public class Slf4jJDKLog {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("lan.bing");
        logger.info("Hello World.");
        logger.info("----1----");
        logger.info("----2----");

        //五月 05, 2020 1:59:01 下午 com.bing.lan.log.jdk.Slf4jJDKLog main
        //信息: Hello World.
        //五月 05, 2020 1:59:01 下午 com.bing.lan.log.jdk.Slf4jJDKLog main
        //信息: ----1----
        //五月 05, 2020 1:59:01 下午 com.bing.lan.log.jdk.Slf4jJDKLog main
        //信息: ----2----
    }
}
