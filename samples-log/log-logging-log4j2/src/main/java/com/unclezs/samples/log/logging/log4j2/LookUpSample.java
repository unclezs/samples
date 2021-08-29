package com.unclezs.samples.log.logging.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * http://logging.apache.org/log4j/2.x/manual/lookups.html
 *
 * @author blog.unclezs.com
 * @date 2020/12/2 12:40 上午
 */
public class LookUpSample {
  public static void main(String[] args) {
    String outStr = "lookup Test";
    // $${ctx:userId}
    ThreadContext.put("userId", "uncle");
    // $${date:MM-dd-yyyy}
    Logger logger = LogManager.getLogger(LookUpSample.class);
    logger.trace(outStr);
    logger.debug(outStr);
    logger.info(outStr);
    logger.warn(outStr);
    logger.error(outStr);
  }
}
