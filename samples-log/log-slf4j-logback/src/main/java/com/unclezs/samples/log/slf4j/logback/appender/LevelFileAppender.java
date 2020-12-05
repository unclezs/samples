package com.unclezs.samples.log.slf4j.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/05 15:15
 */
public class LevelFileAppender extends RollingFileAppender<ILoggingEvent> {


  @Override
  public void start() {
    super.start();
  }
}
