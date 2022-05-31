package com.unclezs.samples.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author blog.unclezs.com
 * @date 2022/5/31 16:34
 */
@Component
public class MailService {
  @Autowired
  UserService userService;

  public String sendMail() {
    ZoneId zoneId = userService.zoneId;
    String dt = ZonedDateTime.now(zoneId).toString();
    return "Hello, it is " + dt;
  }
}
