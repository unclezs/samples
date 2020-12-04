package com.unclezs.samples.log.slf4j.logback.filter;

import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/04 17:26
 */
public class FilterSample {
  public static void main(String[] args) {
    LoggerHelper.reconfigure("logback-filter.xml");
    LoggerHelper.logMsg(FilterSample.class);
  }
}
