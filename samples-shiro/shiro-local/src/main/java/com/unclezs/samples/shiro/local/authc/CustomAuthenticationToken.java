package com.unclezs.samples.shiro.local.authc;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 自定义token
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/14 11:34
 */
public class CustomAuthenticationToken implements RememberMeAuthenticationToken, AuthenticationToken {
  /**
   * 认证token 假设是jwt
   */
  private String token;

  public CustomAuthenticationToken(String token) {
    this.token = token;
  }

  @Override
  public boolean isRememberMe() {
    return false;
  }

  @Override
  public Object getPrincipal() {
    return token;
  }

  @Override
  public Object getCredentials() {
    return token;
  }
}
