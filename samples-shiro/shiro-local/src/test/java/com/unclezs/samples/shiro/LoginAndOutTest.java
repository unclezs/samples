package com.unclezs.samples.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.env.BasicIniEnvironment;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author blog.unclezs.com
 * @since 2020/12/01 16:22
 */
public class LoginAndOutTest {
  @Test
  @SuppressWarnings("deprecated")
  public void loginTest() {
    BasicIniEnvironment environment = new BasicIniEnvironment("classpath:shiro.ini");
    SecurityManager securityManager = environment.getSecurityManager();
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    subject.login(new UsernamePasswordToken("uncle", "123"));
    Assert.assertTrue(subject.isAuthenticated());
    subject.logout();
  }
}
