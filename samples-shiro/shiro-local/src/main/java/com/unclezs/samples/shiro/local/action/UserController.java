package com.unclezs.samples.shiro.local.action;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;

/**
 * @author blog.unclezs.com
 * @since 2020/12/19 13:17
 */
@Slf4j
public class UserController {
  @RequiresRoles("admin")
  public void addUser(String username) {
    log.info("添加用户：{}", username);
  }
}
