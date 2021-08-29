package com.unclezs;

/**
 * @author blog.unclezs.com
 * @date 2020/12/6 12:23 下午
 */
public class User {
  private String age;
  private String name;

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{" +
        "age='" + age + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
