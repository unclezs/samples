package com.unclezs.samples.log.slf4j.logback.filter;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * @author blog.unclezs.com
 * @date 2020/12/3 1:05 上午
 */
public class CustomRegularFilter extends Filter<ILoggingEvent> {

  @Override
  public FilterReply decide(ILoggingEvent event) {
    return event.getMessage().contains("different")?FilterReply.DENY:FilterReply.ACCEPT;
  }
}
