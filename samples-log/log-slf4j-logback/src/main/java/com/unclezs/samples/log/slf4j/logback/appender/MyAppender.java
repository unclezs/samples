package com.unclezs.samples.log.slf4j.logback.appender;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;


/**
 * @author blog.unclezs.com
 * @since 2020/12/03 15:02
 */
public class MyAppender extends AppenderBase<ILoggingEvent> {

  static int MAX_COUNT = 10;
  int counter = 0;
  int limit = MAX_COUNT;

  PatternLayoutEncoder encoder;

  @Override
  public void start() {
    if (this.encoder == null) {
      addError("No encoder set for the appender named [" + name + "].");
      return;
    }
    super.start();
  }

  @Override
  public void append(ILoggingEvent event) {
    if (counter >= limit) {
      return;
    }
    //格式化
    String bytes = this.encoder.getLayout().doLayout(event);
    System.out.print(bytes);
    counter++;
  }

  /**
   * 通过getter setter设置
   */
  public PatternLayoutEncoder getEncoder() {
    return encoder;
  }

  public void setEncoder(PatternLayoutEncoder encoder) {
    this.encoder = encoder;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getLimit() {
    return limit;
  }
}
