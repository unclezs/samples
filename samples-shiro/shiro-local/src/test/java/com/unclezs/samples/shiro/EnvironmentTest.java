package com.unclezs.samples.shiro;

import com.unclezs.samples.shiro.local.action.UserController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.env.DefaultEnvironment;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.Test;

import java.util.Map;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 11:40
 */
public class EnvironmentTest {
  @Test
  public void testDefault() {
    // 默认环境
    DefaultEnvironment environment = new DefaultEnvironment();
    // 设置realm
    SimpleAccountRealm realm = new SimpleAccountRealm();
    realm.addAccount("unclezs", "123", "admin");
    // 配置SecurityManager
    DefaultSecurityManager securityManager = new DefaultSecurityManager(realm);
    environment.setSecurityManager(securityManager);
    // 绑定ThreadContext
    ThreadContext.bind(securityManager);
    // 创建subject，并且绑定到TreadContext
    Subject subject = (new Subject.Builder()).buildSubject();
    ThreadContext.bind(subject);
    // 模拟登陆
    UsernamePasswordToken token = new UsernamePasswordToken("unclezs", "123");
    subject.login(token);
    // 查看当前登陆用户
    System.out.println(SecurityUtils.getSubject().getPrincipal());
    // 查看ThreadContext中的内容，SecurityUtils也是查的ThreadContext
    Map<Object, Object> resources = ThreadContext.getResources();
    System.out.println(resources);
    UserController userController = new UserController();
    userController.addUser("unclezs");
  }
}
