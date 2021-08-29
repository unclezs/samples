package com.unclezs.samples.shiro;

import com.unclezs.samples.shiro.local.authc.CustomAuthenticationToken;
import com.unclezs.samples.shiro.local.authc.password.PasswordUtil;
import com.unclezs.samples.shiro.local.realm.CustomTokenRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.env.DefaultEnvironment;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;

/**
 * @author blog.unclezs.com
 * @since 2020/12/19 11:40
 */
@Slf4j
public class EnvironmentTest {
  public static String username = "uncle";
  public static String password = "123";
  private DefaultEnvironment environment;
  private Realm realm;
  private DefaultSecurityManager securityManager;

  @Before
  public void initEnv() {
    // 默认环境
    environment = new DefaultEnvironment();
    // 设置realm
    realm = initRealm();
    // 配置SecurityManager
    securityManager = new DefaultSecurityManager(realm);
    environment.setSecurityManager(securityManager);
    // 绑定ThreadContext
    ThreadContext.bind(securityManager);
  }

  /**
   * 自定义 realm
   *
   * @return /
   */
  private Realm initRealm() {
    CustomTokenRealm realm = new CustomTokenRealm();
    HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(PasswordUtil.ALGORITHM);
    // 这里可以配置数据库里保存的密码 是Hex还是Base64，默认true
    hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
    // 使用MD5加密验证
    realm.setCredentialsMatcher(hashedCredentialsMatcher);
    // 设置支持的token类型 用于support方法
    realm.setAuthenticationTokenClass(CustomAuthenticationToken.class);
    //
    return realm;
  }

  @Test
  public void runTest() {
    login(username, password);
    // 查看当前登陆用户
    log.info("用户登录成功：{}", SecurityUtils.getSubject().getPrincipal());
  }

  private void login(String username, String password) {
    // 创建subject，并且绑定到TreadContext
    Subject subject = (new Subject.Builder()).buildSubject();
    ThreadContext.bind(subject);
    // 模拟登陆
    CustomAuthenticationToken token = new CustomAuthenticationToken(username, PasswordUtil.encrypt(password, "salt"));
    subject.login(token);
  }
}
