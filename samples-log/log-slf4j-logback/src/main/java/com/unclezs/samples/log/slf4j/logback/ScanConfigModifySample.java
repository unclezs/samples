package com.unclezs.samples.log.slf4j.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/02 19:54
 */
public class ScanConfigModifySample {
  public static void main(String[] args) throws InterruptedException {
    Logger logger = LoggerFactory.getLogger(ScanConfigModifySample.class);
    while (true) {
      logger.info("scan period by 1 seconds");
      Thread.sleep(2000);
    }
  }
}
