package com.unclezs.samples.mbean.mbeans;

/**
 * @author blog.unclezs.com
 * @since 2021/10/8 17:27
 */
public interface CarMBean {
  void run();

  String getId();

  void setId(String id);

  void console();
}
