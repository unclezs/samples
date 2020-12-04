package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author blog.unclezs.com
 * @since 2020/11/30 18:58
 */
public class BooleanSample {
  /**
   * 设置为arity=1,则代表需要在-debug 后面跟true或者false
   */
  @Parameter(names = {"-debug", "-d"}, arity = 1)
  private boolean debug = true;

  /**
   * 省略arity代表 如果传入-verbose或者-v则设置verbose为false
   */
  @Parameter(names = {"-verbose", "-v"}, arity = 0)
  private boolean verbose = true;

  public static void main(String... argv) {
    BooleanSample booleanTest = new BooleanSample();
    JCommander.newBuilder()
        .verbose(1)
        .addObject(booleanTest)
        .build()
        .parse(argv);
    System.out.println("debug:" + booleanTest.debug);
    System.out.println("verbose: " + booleanTest.verbose);
  }
}
