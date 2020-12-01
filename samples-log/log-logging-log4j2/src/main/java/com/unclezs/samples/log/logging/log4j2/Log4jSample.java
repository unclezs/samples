package com.unclezs.samples.log.logging.log4j2;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author blog.unclezs.com
 * @date 2020/12/1 10:46 下午
 */
public class Log4jSample {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(Log4jSample.class);
        logger.info("log by log4j2");
        logger.warn("log by log4j2");
        logger.error("log by log4j2");
        logger.debug("log by log4j2");
        logger.trace("log by log4j2");
    }
}
