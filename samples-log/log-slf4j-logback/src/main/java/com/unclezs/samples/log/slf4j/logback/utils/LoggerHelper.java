package com.unclezs.samples.log.slf4j.logback.utils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * logback辅助类
 *
 * @author blog.unclezs.com
 * @since 2020/12/04 11:02
 */
public class LoggerHelper {
  /**
   * 重新加载配置，清除默认配置
   *
   * @param configInClasspath 在classpath下的配置,如果为空则清空当前配置
   */
  public static LoggerContext reconfigure(String configInClasspath) {
    //这里不能直接new，因为还做了一些初始化的工作
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    try {
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);
      context.reset();
      if (configInClasspath != null &&!configInClasspath.isEmpty()) {
        configurator.doConfigure(LoggerHelper.class.getResource("/" + configInClasspath));
      }
    } catch (JoranException ignored) {
      StatusPrinter.print(context);
    }
    return context;
  }

  /**
   * 输出日志方便测试
   *
   * @param logger 记录器
   */
  public static void logMsg(Logger logger) {
    logger.trace("test message by different level");
    logger.debug("test message by different level");
    logger.info("test message by different level");
    logger.warn("test message by different level");
    logger.error("test message by different level");
  }

  /**
   * 输出日志方便测试
   *
   * @param loggerName 记录器类
   */
  public static void logMsg(Class<?> loggerName) {
    logMsg(LoggerFactory.getLogger(loggerName));
  }
}
