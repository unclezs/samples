package com.unclezs.test.junit;

import lombok.Getter;

/**
 * @author blog.unclezs.com
 * @since 2022/7/26 11:47
 */
@Getter
public enum EnumInstance {
  INSTANCE;

  private String name;
  private String age;

  static {
    INSTANCE.name = "unclezs";
    INSTANCE.age = "26";
  }

}
