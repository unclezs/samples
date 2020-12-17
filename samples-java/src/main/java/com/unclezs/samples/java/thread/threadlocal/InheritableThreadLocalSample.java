package com.unclezs.samples.java.thread.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/17 16:54
 */
@Slf4j
public class InheritableThreadLocalSample {
  private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void main(String[] args) {
    Thread parent = new Thread(() -> {
      threadLocal.set("uncle");
      inheritableThreadLocal.set("uncle");
      log.info("父线程的值：threadLocal:{},inheritableThreadLocal:{}", threadLocal.get(),inheritableThreadLocal.get());
      Thread child = new Thread(() -> {
        log.info("父线程的值：threadLocal:{},inheritableThreadLocal:{}", threadLocal.get(),inheritableThreadLocal.get());
      },"child thread");
      child.start();
    },"parent thread");
    parent.start();
  }

}
