package com.unclezs.samples.shiro.local;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/14 11:19
 */
public class ShiroSample {
  public static void main(String[] args) {
    SecurityManager manager = new DefaultSecurityManager();

    SecurityUtils.setSecurityManager(manager);

  }
}
