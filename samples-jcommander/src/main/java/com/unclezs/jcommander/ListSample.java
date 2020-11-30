package com.unclezs.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/11/30 19:22
 */
public class ListSample {
  @Parameter(names = "-host", description = "The host")
  private List<String> hosts;

  public static void main(String... argv) {
    String[] args = {"-host","127.0.0.1","-host","127.0.0.2"};
    ListSample main = new ListSample();
    JCommander.newBuilder()
        .verbose(1)
        .addObject(main)
        .build()
        .parse(args);
    System.out.println("verbose: " + main.hosts);
  }
}
