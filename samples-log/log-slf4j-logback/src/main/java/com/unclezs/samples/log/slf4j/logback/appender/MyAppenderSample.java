package com.unclezs.samples.log.slf4j.logback.appender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/03 19:37
 */
public class MyAppenderSample {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(MyAppenderSample.class);
    for (int i = 0; i < 5; i++) {
      logger.info("第{}次", i);
    }
  }
}
