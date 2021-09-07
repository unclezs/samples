package com.unclezs.spring.di.autowired;

import com.unclezs.spring.di.autowired.model.Bean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author blog.unclezs.com
 * @date 2021/09/07
 */
@Component
public class SpecialBean implements InitializingBean {
  @Autowired
  private Map<String, Bean> map;
  @Autowired
  private List<Bean> list;


  @Override
  public void afterPropertiesSet() {
    System.out.println(map);
    System.out.println(list);
  }
}
