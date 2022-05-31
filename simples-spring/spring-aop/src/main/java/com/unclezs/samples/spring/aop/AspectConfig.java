package com.unclezs.samples.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author unclezs
 * @date 2022/5/31 16:35
 */
@Aspect
@Component
public class AspectConfig {
//  @Before("execution(public * com..*.UserService.*(..))")
//  public void doAccessCheck() {
//    System.err.println("[Before] do access check...");
//  }

  @Before("execution(public * com..*.TransactionService.doTransaction(..))")
  public void doAA(){

  }
}