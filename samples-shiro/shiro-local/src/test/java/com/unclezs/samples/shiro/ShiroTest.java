package com.unclezs.samples.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author blog.unclezs.com
 * @since 2020/12/14 11:38
 */
@Slf4j
public class ShiroTest {
  @Before
  public void init() {
    SimpleAccountRealm realm = new SimpleAccountRealm();
    realm.addAccount("uncle", "123", "admin");
    SecurityManager securityManager = new DefaultSecurityManager(realm);
    SecurityUtils.setSecurityManager(securityManager);
  }

  @Test
  public void test() {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken token = new UsernamePasswordToken("uncle", "123");
    subject.login(token);
    Assert.assertTrue(subject.isAuthenticated());
  }

  @Test
  public void testCodec() {

  }
}
