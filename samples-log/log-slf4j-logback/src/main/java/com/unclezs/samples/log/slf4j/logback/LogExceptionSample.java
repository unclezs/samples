package com.unclezs.samples.log.slf4j.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author blog.unclezs.com
 * @date 2020/12/2 11:34 下午
 */
public class LogExceptionSample {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogExceptionSample.class);
        logger.trace("test",new RuntimeException("error"));
    }
}
