package com.unclezs.jcommander;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author blog.unclezs.com
 * @since 2020/11/30 19:22
 */
public class MapSample {
  @Parameter(names = {"-cp",
      "--custom_params"}, description = "custom_params", converter = PairConverter.class)
  private List<Pair> customParams;

  public static void main(String... argv) {
    String[] args = {"-cp", "ip:127.0.0.1:3:5", "-cp", "port:8080"};
    MapSample main = new MapSample();
    JCommander.newBuilder()
        .verbose(1)
        .addObject(main)
        .build()
        .parse(args);
    System.out.println("verbose: " + main.customParams);
  }

  static class PairConverter implements IStringConverter<Pair> {

    @Override
    public Pair convert(String param) {
      int keyIndex = param.indexOf(":");
      String key = param.substring(0, keyIndex);
      String value = param.substring(keyIndex + 1);
      return new Pair(key, value);
    }
  }


  @Data
  @AllArgsConstructor
  static class Pair {
    String key;
    String value;
  }
}
