package com.unclezs.samples.spring.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author blog.unclezs.com
 * @date 2022/5/31 16:39
 */
@Component
public class TransactionService {
  @Autowired
  private UserService userService;

  public void doTransaction() {
    System.out.println(userService);
    System.out.println(((TransactionService) AopContext.currentProxy()));
    ((TransactionService) AopContext.currentProxy()).rollback();
  }

  private void rollback() {
    System.out.println(userService);
    System.out.println("123");
  }
}
