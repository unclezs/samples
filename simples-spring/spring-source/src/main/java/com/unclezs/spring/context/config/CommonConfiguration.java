package com.unclezs.spring.context.config;

import com.unclezs.spring.context.service.AppService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

/**
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
@Configuration
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommonConfiguration {

  @Bean
  @Scope("singleton")
  public AppService appService() {
    return new AppService();
  }
}
