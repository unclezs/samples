package com.unclezs.test.junit;

/**
 * @author blog.unclezs.com
 * @since 2022/7/25 10:48
 */
public class SUtils {
  private SUtils() {
    throw new RuntimeException();
  }

  public static int a() {
    System.out.println("aaaaaaaaaa");
    System.out.println("a");
    if (1 > 0) {
      throw new RuntimeException();
    }
    return 2;
  }

  public static int b() {
    System.out.println("b");
    return 2;
  }
}
