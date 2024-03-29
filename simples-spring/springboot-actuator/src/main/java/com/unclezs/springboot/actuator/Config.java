package com.unclezs.springboot.actuator;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author blog.unclezs.com
 * @since 2022/7/6 09:13
 */
@Configuration
public class Config {

  @Bean
  MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
    return (registry) -> registry.config().commonTags("application", applicationName);
  }
}
