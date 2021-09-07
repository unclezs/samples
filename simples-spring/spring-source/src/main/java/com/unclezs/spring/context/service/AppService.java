package com.unclezs.spring.context.service;

import lombok.Getter;
import lombok.Setter;

/**
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
@Setter
@Getter
public class AppService {
  private Boolean useCache = false;

  public void doService() {
    if (Boolean.TRUE.equals(useCache)) {
      System.out.println("我是缓存");
    } else {
      System.out.println("我是实时数据");
    }
  }
}
