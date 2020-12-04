package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author blog.unclezs.com
 * @since 2020/11/30 19:20
 */
public class IntegerSample {
  @Parameter(names = {"-verbose", "-v"},description = "log level")
  private int verbose;

  public static void main(String... argv) {
    IntegerSample main = new IntegerSample();
    JCommander.newBuilder()
        .verbose(1)
        .addObject(main)
        .build()
        .parse(argv);
    System.out.println("verbose: " + main.verbose);
  }
}
