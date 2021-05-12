package com.bing.lan.log.jdk;

import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;

public class MyConsoleHandler extends ConsoleHandler {

  @Override
  public void publish(LogRecord record) {
    super.publish(record);
    System.out.println("publish(): " + record.getMessage());
  }
}
