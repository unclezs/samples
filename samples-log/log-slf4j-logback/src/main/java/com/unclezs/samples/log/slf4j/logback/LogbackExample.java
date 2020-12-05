package com.unclezs.samples.log.slf4j.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

import java.time.LocalDateTime;
import java.util.concurrent.locks.LockSupport;

/**
 * 实际使用的例子
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/05 10:56
 */
public class LogbackExample {
  public static void main(String[] args) throws Exception {
    LoggerContext context = LoggerHelper.reconfigure("logback-template.xml");
    Logger logger = context.getLogger(LogbackExample.class);
    for (int i = 0; i < 1000; i++) {
      Thread.sleep(1000);
      logger.info(LocalDateTime.now().toString());
      logger.error(LocalDateTime.now().toString());
      logger.warn(LocalDateTime.now().toString());
      logger.debug(LocalDateTime.now().toString());
    }
  }
}
