package com.unclezs.samples.spring.aop;

import org.springframework.stereotype.Component;

import java.time.ZoneId;

/**
 * @author blog.unclezs.com
 * @date 2022/5/31 16:32
 */
@Component
public class UserService {
  public final ZoneId zoneId = ZoneId.systemDefault();

  public UserService() {
    System.out.println("UserService(): init...");
    System.out.println("UserService(): zoneId = " + this.zoneId);
  }

  public ZoneId getZoneId() {
    return zoneId;
  }

  public final ZoneId getFinalZoneId() {
    return zoneId;
  }
}
