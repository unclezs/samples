package com.unclezs.samples.java.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author blog.unclezs.com
 * @since 2021/12/3 16:25
 */
public class PeekSample {
  public static void main(String[] args) {
    List<String> collect = Stream.of("three")
        .filter(e -> e.length() > 3)
        .peek(e -> System.out.println("Filtered value: " + e))
        .map(String::toUpperCase)
        .peek(e -> System.out.println("Mapped value: " + e))
        .collect(Collectors.toList());
    System.out.println(collect);
  }
}
