package com.unclezs.samples.log.slf4j.logback.converter;

import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 17:03
 */
public class CustomNowTimeConverterSample {
  public static void main(String[] args) {
    LoggerHelper.reconfigure("logback-converter.xml");
    LoggerHelper.logMsg(CustomNowTimeConverterSample.class);
  }
}
