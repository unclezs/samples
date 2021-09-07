package com.unclezs.spring.context.config;

import com.unclezs.spring.context.config.condition.DevCondition;
import org.springframework.context.annotation.Conditional;

/**
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
@Conditional({DevCondition.class})
public class ConditionalConfiguration {
}
