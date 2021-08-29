package com.unclezs.samples.log.slf4j.logback.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

/**
 * @author blog.unclezs.com
 * @date 2020/12/4 10:32 下午
 */
public class CustomTurboFilter extends TurboFilter {
  @Override
  public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
    if (t == null) {
      return FilterReply.DENY;
    }
    return FilterReply.ACCEPT;
  }
}
