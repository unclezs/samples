package com.unclezs.spring.context.config.condition;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * 指定在什么阶段进行匹配
 * <p>
 * 1. 解析阶段
 * <p>
 * 2. 注册bean阶段
 *
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
public class DevConfigurationCondition implements ConfigurationCondition {
  @Override
  public ConfigurationPhase getConfigurationPhase() {
    return ConfigurationPhase.PARSE_CONFIGURATION;
  }

  @Override
  public boolean matches(@Nullable ConditionContext context, @Nullable AnnotatedTypeMetadata metadata) {
    if (context == null) {
      return false;
    }
    return Arrays.stream(context.getEnvironment().getActiveProfiles()).allMatch("dev"::equals);
  }
}
