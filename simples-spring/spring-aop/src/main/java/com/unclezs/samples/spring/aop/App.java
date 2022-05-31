package com.unclezs.samples.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author blog.unclezs.com
 * @date 2022/5/31 16:33
 */
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableTransactionManagement(proxyTargetClass = true)
public class App {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
//    MailService mailService = context.getBean(MailService.class);
//    System.out.println(mailService.sendMail());
    context.getBean(TransactionService.class).doTransaction();
  }
}
