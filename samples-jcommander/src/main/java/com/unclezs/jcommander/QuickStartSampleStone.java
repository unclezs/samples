package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author blog.unclezs.com
 * @since 2020/11/30 18:40
 */
class QuickStartSampleStone {
  @Parameter(names = {"--length", "-l"})
  int length;
  @Parameter(names = {"--pattern", "-p"})
  int pattern;

  public static void main(String... argv) {
    QuickStartSampleStone main = new QuickStartSampleStone();
    JCommander.newBuilder()
        .addObject(main)
        .build()
        .parse(argv);
    main.run();
  }

  public void run() {
    System.out.printf("%d %d", length, pattern);
  }
}