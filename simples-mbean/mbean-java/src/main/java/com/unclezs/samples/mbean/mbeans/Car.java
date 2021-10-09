package com.unclezs.samples.mbean.mbeans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author blog.unclezs.com
 * @since 2021/10/8 17:31
 */
@Getter
@Setter
@ToString
public class Car implements CarMBean{
  private String id;

  @Override
  public void run() {
    System.out.println("run");
  }

  @Override
  public void console() {
    System.out.println(this);
  }
}
