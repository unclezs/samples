package com.unclezs.samples.log.slf4j.logback.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 17:03
 */
public class CustomNowTimeConverter extends ClassicConverter {

  @Override
  public String convert(ILoggingEvent event) {
    return String.valueOf(System.currentTimeMillis());
  }
}
