package com.bing.lan.log.jdk;

import java.util.logging.Logger;

/**
 * Created by 蓝兵 on 2019/6/21.
 */

public class JDKLog {

    public static void main(String[] args) {
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
