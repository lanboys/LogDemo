package com.bing.lan.log.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by 蓝兵 on 2019/6/21.
 * <p>
 * 配置文件名称在 {@link org.apache.logging.log4j.core.config.ConfigurationFactory.Factory}
 * #getConfiguration(LoggerContext,boolean,String) 方法中定义
 */

public class Log4j2Log {

    public static void main(String args[]) {
        Logger logger = LogManager.getLogger(Log4j2Log.class);
        logger.debug("Debug Level");
        logger.info("Info Level");
        logger.warn("Warn Level");
        logger.error("Error Level");
        //21:14:33.526 [main] INFO  com.bing.lan.log.log4j.Log4j2Log - Info Level
        //21:14:33.528 [main] WARN  com.bing.lan.log.log4j.Log4j2Log - Warn Level
        //21:14:33.528 [main] ERROR com.bing.lan.log.log4j.Log4j2Log - Error Level
    }

    /**
     * 自定义 配置，取代默认配置
     *
     * @param url 配置文件 URl
     */
    public void reconfig(URL url) {
        try {
            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            InputStream stream = url.openStream();
            ConfigurationSource source = new ConfigurationSource(stream, url);
            ctx.start(ConfigurationFactory.getInstance().getConfiguration(ctx, source));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
