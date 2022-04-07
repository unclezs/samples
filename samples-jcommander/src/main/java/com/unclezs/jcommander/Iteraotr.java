package com.unclezs.jcommander;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author blog.unclezs.com
 * @since 2021/12/27 19:49
 */
public class Iteraotr {
  public static void main(String[] args) {
    ArrayList<Integer> objects = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      objects.add(i);
    }

    Iterator<Integer> iterator = objects.iterator();
    while (iterator.hasNext()) {
      Integer next = iterator.next();
      if (next % 2 == 0) {
        System.out.println(next);
      } else {
        iterator.remove();
      }
    }
  }
}
