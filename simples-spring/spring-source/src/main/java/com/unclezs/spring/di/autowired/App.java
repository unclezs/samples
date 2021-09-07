package com.unclezs.spring.di.autowired;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author blog.unclezs.com
 * @date 2021/09/07
 */
public class App {
  public static void main(String[] args) throws InterruptedException {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext("com.unclezs.spring.di.autowired");
    Thread.currentThread().join();
  }
}
