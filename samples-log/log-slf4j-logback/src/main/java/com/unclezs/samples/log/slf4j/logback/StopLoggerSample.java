package com.unclezs.samples.log.slf4j.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

/**
 * @author blog.unclezs.com
 * @date 2020/12/2 11:59 下午
 */
public class StopLoggerSample {
  public static void main(String[] args) {
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    Logger logger = context.getLogger("ROOT");
    logger.info("stop start");
    context.stop();
    //不显示
    logger.info("is stop?");
  }
}
