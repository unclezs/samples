package com.unclezs.samples.log.slf4j.logback.level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/02 16:59
 */
public class LevelSample {
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger("com.unclezs.samples.log.slf4j.logback.level");
    logger.trace("log by Logback");
    logger.info("log by Logback");
    logger.warn("log by Logback");
    logger.error("log by Logback");
    logger.debug("log by Logback");
  }
}
