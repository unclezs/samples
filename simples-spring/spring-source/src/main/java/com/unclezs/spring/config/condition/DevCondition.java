package com.unclezs.spring.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.Nullable;

import java.util.Arrays;

/**
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
public class DevCondition implements Condition {
  @Override
  public boolean matches(@Nullable ConditionContext context, @Nullable AnnotatedTypeMetadata metadata) {
    if (context == null) {
      return false;
    }
    return Arrays.stream(context.getEnvironment().getActiveProfiles()).allMatch("dev"::equals);
  }
}
