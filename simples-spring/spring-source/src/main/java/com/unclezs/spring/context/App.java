package com.unclezs.spring.context;

import com.unclezs.spring.context.config.CommonConfiguration;
import com.unclezs.spring.context.service.AppService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author blog.unclezs.com
 * @date 2021/8/29
 */
public class App {
  public static void main(String[] args) {
    AnnotationConfigApplicationContext parent = new AnnotationConfigApplicationContext();
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.setParent(parent);
    parent.register(CommonConfiguration.class);
    context.register(CommonConfiguration.class);
    parent.refresh();
    context.refresh();

    AppService service = parent.getBean(AppService.class);
    service.setUseCache(true);
    service.doService();

    AppService appService = context.getBean(AppService.class);
    appService.doService();
  }
}
