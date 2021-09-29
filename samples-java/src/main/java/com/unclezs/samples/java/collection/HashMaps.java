package com.unclezs.samples.java.collection;

import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author blog.unclezs.com
 * @since 2021/9/17 16:10
 */
public class HashMaps {
  public static void main(String[] args) {
    StringJoiner joiner = new StringJoiner("_");
    joiner.add("123");
    joiner.add(null);
    System.out.println(joiner.toString());
  }
}
