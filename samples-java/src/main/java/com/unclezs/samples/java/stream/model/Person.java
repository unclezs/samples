package com.unclezs.samples.java.stream.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author blog.unclezs.com
 * @since 2021/11/24 17:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  private String name;
  private int age;
  private int sex;
}
