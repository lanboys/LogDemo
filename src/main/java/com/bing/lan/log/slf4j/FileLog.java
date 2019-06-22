package com.bing.lan.log.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 蓝兵 on 2019/6/22.
 */


public class FileLog {

    public FileLog() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.warn("File Log Test");
    }
}

