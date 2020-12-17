package com.unclezs.samples.shiro.local.realm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/14 11:58
 */
@Slf4j
public class CustomTokenRealm extends AuthorizingRealm {

  @Override
  public boolean supports(AuthenticationToken token) {
    return true;
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
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
      throws AuthenticationException {
    String token = authenticationToken.getPrincipal().toString();
    if (!"uncle".equals(token)) {
      throw new AuthenticationException("认证失败");
    }
    return new SimpleAuthenticationInfo(token, token, getName());
  }
}
