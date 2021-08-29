package com.unclezs.samples.log.slf4j.logback.configuration;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author blog.unclezs.com
 * @date 2020/12/2 11:46 下午
 */
public class JoranConfigurationSample {
  final static Logger logger = LoggerFactory.getLogger(JoranConfigurationSample.class);

  public static void main(String[] args) {
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
    try {
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);
      logger.info("reset before config");
      context.reset();
      configurator.doConfigure(JoranConfigurationSample.class.getResource("/logback-configuration.xml"));
    } catch (JoranException je) {
      // StatusPrinter will handle this
    }
//        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    logger.info("Entering application.");

    System.out.println("xxx");
    logger.info("Exiting application.");
  }
}
