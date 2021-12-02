package com.unclezs.samples.java.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * +XX
 *
 * @author blog.unclezs.com
 * @since 2021/12/2 10:55
 */
public class OutOfMemorySample {
  public static void main(String[] args) throws InterruptedException {
    List<Byte[]> dump = new ArrayList<>();
    // 10M
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(() -> {
      for (int i = 0; i < 15; i++) {
        Byte[] bytes = new Byte[1024 * 1024 * 10];
        dump.add(bytes);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("等待中 " + i);
      }
    });
    Thread.currentThread().join();
  }
}
