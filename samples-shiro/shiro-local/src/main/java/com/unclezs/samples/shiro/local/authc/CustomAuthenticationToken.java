package com.unclezs.samples.shiro.local.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义token
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/14 11:34
 */
public class CustomAuthenticationToken extends UsernamePasswordToken {
  //do something


  public CustomAuthenticationToken(String username, String password) {
    super(username, password);
  }
}
