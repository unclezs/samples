package com.unclezs.samples.log.slf4j.logback;

import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author blog.unclezs.com
 * @date 2020/12/4 11:14 下午
 */
public class MdcSample {
  public static void main(String[] args) {
    LoggerHelper.reconfigure("logback-mdc.xml");
    Logger logger = LoggerFactory.getLogger(MdcSample.class);
    MDC.put("userId", "uncle");
    MDC.put("signed", "true");
    logger.info("user visited home page..");
  }
}
