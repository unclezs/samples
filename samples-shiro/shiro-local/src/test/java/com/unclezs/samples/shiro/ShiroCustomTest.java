package com.unclezs.samples.shiro;

import com.unclezs.samples.shiro.local.realm.CustomTokenRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Before;

/**
 * @author blog.unclezs.com
 * @since 2020/12/14 11:38
 */
public class ShiroCustomTest {
  @Before
  public void init() {
    SecurityManager securityManager = new DefaultSecurityManager(new CustomTokenRealm());
    SecurityUtils.setSecurityManager(securityManager);
  }

}
