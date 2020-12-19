package com.unclezs.samples.shiro.local.authc.password;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.codec.CodecSupport;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 17:32
 */
@Slf4j
public class CustomPasswordMatcher implements CredentialsMatcher {
  @Override
  public boolean doCredentialsMatch(AuthenticationToken tk, AuthenticationInfo info) {
    String submitPassword = getSubmitPassword(tk);
    String originalPassword = getPassword(info);
    String salt = getSalt(info);
    try {
      return PasswordUtil.validate(submitPassword, originalPassword, salt);
    } catch (Exception e) {
      log.error("can not get proprate signature.", e);
      throw new IllegalArgumentException("密码错误");
    }
  }

  private String getSubmitPassword(AuthenticationToken token) {
    Object password = token != null ? token.getCredentials() : null;
    return password != null ? new String((char[]) password) : null;
  }

  private String getSalt(AuthenticationInfo info) {
    return CodecSupport.toString(this.getSimpleAuthenticationInfo(info).getCredentialsSalt().getBytes());
  }

  private String getPassword(AuthenticationInfo info) {
    return (String) this.getSimpleAuthenticationInfo(info).getCredentials();
  }

  private SimpleAuthenticationInfo getSimpleAuthenticationInfo(AuthenticationInfo info) {
    if (info instanceof SimpleAuthenticationInfo) {
      return (SimpleAuthenticationInfo) info;
    } else {
      log.error("AuthenticationInfo 不是 SimpleAuthenticationInfo");
      throw new IllegalArgumentException("错误的类型：AuthenticationInfo");
    }
  }
}
