package com.unclezs.samples.log.slf4j.logback;

import ch.qos.logback.core.status.StatusManager;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author blog.unclezs.com
 * @date 2020/12/1 10:46 下午
 */
public class LogbackSample {
    static final Logger logger = LoggerFactory.getLogger(LogbackSample.class);

    public static void main(String[] args) {
        logger.info("log by Logback");
        logger.warn("log by Logback");
        logger.error("log by Logback");
        logger.debug("log by Logback");
        logger.trace("log by Logback");
    }
}
