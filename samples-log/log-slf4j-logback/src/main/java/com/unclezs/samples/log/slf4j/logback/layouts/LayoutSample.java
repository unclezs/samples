package com.unclezs.samples.log.slf4j.logback.layouts;

import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 11:17
 */
public class LayoutSample {
  public static void main(String[] args) {
    LoggerHelper.reconfigure("logback-layouts.xml");
    LoggerHelper.logMsg(LayoutSample.class);
  }
}
