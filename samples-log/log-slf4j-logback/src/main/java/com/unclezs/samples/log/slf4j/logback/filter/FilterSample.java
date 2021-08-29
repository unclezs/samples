package com.unclezs.samples.log.slf4j.logback.filter;

import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 17:26
 */
public class FilterSample {
  public static void main(String[] args) {
    LoggerHelper.reconfigure("logback-filter.xml");
    Logger logger = LoggerFactory.getLogger(FilterSample.class);
    logger.info("different log");
    logger.info("same log");
    logger.error("error msg", new RuntimeException("error"));
  }
}
