package com.unclezs.samples.log.slf4j.logback.layouts;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 11:33
 */
public class MyLayout extends LayoutBase<ILoggingEvent> {
  @Override
  public String doLayout(ILoggingEvent event) {
    return "自定义布局器：" + (event.getTimeStamp() - event.getLoggerContextVO().getBirthTime())
        + " "
        + event.getLevel()
        + " ["
        + event.getThreadName()
        + "] "
        + event.getLoggerName()
        + " - "
        + event.getFormattedMessage()
        + CoreConstants.LINE_SEPARATOR;
  }
}
