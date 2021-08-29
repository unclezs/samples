package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.unclezs.jcommander.validator.PasswordValidator;

/**
 * @author blog.unclezs.com
 * @date 2020/12/1 12:04 上午
 */
public class ParameterValidatorSample {
  @Parameter(names = "-pwd", description = "The password", password = true, echoInput = true, validateWith = {
      PasswordValidator.class, PasswordValidator.class})
  public String password;

  public static void main(String... argv) {
    String[] args = {"-pwd"};
    ParameterValidatorSample main = new ParameterValidatorSample();
    JCommander.newBuilder()
        .verbose(1)
        .addObject(main)
        .build()
        .parse(args);
    System.out.println("verbose: " + main.password);
  }
}
