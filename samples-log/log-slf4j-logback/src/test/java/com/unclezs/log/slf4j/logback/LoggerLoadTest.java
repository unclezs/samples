package com.unclezs.log.slf4j.logback;

import org.junit.Test;
import org.slf4j.impl.StaticLoggerBinder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author blog.unclezs.com
 * @since 2020/12/04 14:32
 */
public class LoggerLoadTest {
  /**
   * @throws Exception /
   * @see org.slf4j.LoggerFactory:bind()
   */
  @Test
  public void testMultiLoggerImplInLib() throws Exception {
    Enumeration<URL> resources =
        LoggerLoadTest.class.getClassLoader().getResources("org/slf4j/impl/StaticLoggerBinder.class");
    while (resources.hasMoreElements()) {
      System.out.println(resources.nextElement());
    }
    //自动选择最先加载的
    System.out.println(StaticLoggerBinder.getSingleton().getLoggerFactoryClassStr());
    //通过classLoader的方式看看哪个先加载，最终得出，先引入的先加载
    Class<?> clazz = Class.forName("org.slf4j.impl.StaticLoggerBinder");
    Constructor<?> constructor = clazz.getDeclaredConstructor();
    constructor.setAccessible(true);
    Method method = clazz.getMethod("getLoggerFactoryClassStr");
    method.setAccessible(true);
    System.out.println(method.invoke(constructor.newInstance()));
  }
}
