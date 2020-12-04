package com.unclezs.samples.log.slf4j.logback.layouts;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 15:08
 */
public class PatternLayoutSample {
  public static void main(String[] args) {
    LoggerContext context = LoggerHelper.reconfigure(null);
    Logger rootLogger = context.getLogger("ROOT");
    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
    encoder.setContext(context);
    encoder.setPattern("%-5level [%thread]: %message%n");
    encoder.start();
    ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();
    appender.setContext(context);
    appender.setEncoder(encoder);
    appender.start();
    rootLogger.addAppender(appender);
    rootLogger.debug("Message 1");
    rootLogger.warn("Message 2");
  }
}
