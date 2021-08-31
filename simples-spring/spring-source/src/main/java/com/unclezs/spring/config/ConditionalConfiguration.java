package com.unclezs.spring.config;

import com.unclezs.spring.config.condition.DevCondition;
import org.springframework.context.annotation.Conditional;

/**
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
@Conditional({DevCondition.class})
public class ConditionalConfiguration {
}
