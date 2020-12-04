package com.unclezs.samples.log.slf4j.logback;

import com.unclezs.samples.log.slf4j.logback.utils.LoggerHelper;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 16:13
 */
public class EvaluatorsSample {
  public static void main(String[] args) {
    LoggerHelper.reconfigure("logback-eval.xml");
    LoggerHelper.logMsg(EvaluatorsSample.class);
  }
}
