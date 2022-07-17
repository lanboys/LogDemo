package com.bing.lan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 */
public class App {

  public static void main(String[] args) {
    System.out.println("Hello World!");

    Logger logger = LoggerFactory.getLogger(App.class);
    logger.trace("Trace Level.");
    logger.debug("Debug Level.");
    logger.info("Info Level.");
    logger.warn("Warn Level.");
    logger.error("Error Level.");

    logger = LoggerFactory.getLogger(Integer.class);
    logger.trace("Trace Level.");
    logger.debug("Debug Level.");
    logger.info("Info Level.");
    logger.warn("Warn Level.");
    logger.error("Error Level.");
  }
}
