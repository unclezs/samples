package com.unclezs.samples.log.slf4j.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

/**
 * @author blog.unclezs.com
 * @date 2020/12/2 1:42 上午
 */
public class LogBackContextSample {
    public static void main(String[] args) {
        // print internal state
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
