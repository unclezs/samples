package com.unclezs.samples.mapstruct.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/05 18:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  private String name;
  private User user;
}
