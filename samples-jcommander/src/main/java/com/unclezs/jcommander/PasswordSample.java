package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * 将密码隐藏 密码字段直接输入
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/11/30 19:22
 */
public class PasswordSample {
  @Parameter(names = "-pwd", description = "The password", password = true)
  private String password;

  public static void main(String... argv) {
    String[] args = {"-pwd"};
    PasswordSample main = new PasswordSample();
    JCommander.newBuilder()
        .verbose(1)
        .addObject(main)
        .build()
        .parse(args);
    System.out.println("verbose: " + main.password);
  }
}
