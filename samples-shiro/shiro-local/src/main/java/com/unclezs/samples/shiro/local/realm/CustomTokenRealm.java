package com.unclezs.samples.shiro.local.realm;

import com.unclezs.samples.shiro.local.authc.CustomAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author blog.unclezs.com
 * @since 2020/12/14 11:58
 */
@Slf4j
public class CustomTokenRealm extends AuthorizingRealm {

  /**
   * 只支持CustomAuthenticationToken
   *
   * @param token jwt token
   * @return /
   */
  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof CustomAuthenticationToken;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    authorizationInfo.addStringPermission("add");
    authorizationInfo.addStringPermission("view");
    authorizationInfo.addRole("admin");
    return authorizationInfo;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
    CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authenticationToken;
    String token = customAuthenticationToken.getUsername();
    if (!"uncle".equals(token)) {
      log.error("用户不存在：{}", token);
      throw new AuthenticationException("用户不存在");
    }
    // 模拟盐为salt
    return new SimpleAuthenticationInfo(customAuthenticationToken.getUsername(),
        customAuthenticationToken.getPassword(), ByteSource.Util.bytes("salt"),
        getName());
  }

}
