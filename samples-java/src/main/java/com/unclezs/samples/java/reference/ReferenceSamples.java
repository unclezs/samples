package com.unclezs.samples.java.reference;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/**
 * 几种引用类型测试
 * <p>
 * 指定VM参数 -Xms20m -Xmx20m -XX:+PrintGCDetails -verbose:gc
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/17 13:56
 */
@Slf4j
public class ReferenceSamples{
  private static final int SIZE1MB = 1024 * 1024;
  private static final int SIZE1KB = 1024;

  public static void main(String[] args) throws InterruptedException {
    // 引用队列，存放Reference对象
    ReferenceQueue<Byte[]> queue = new ReferenceQueue<>();
    // 定义四种引用对象，强/弱/虚引用为1kb，软引用为1mb
    Byte[] strong = new Byte[SIZE1KB];
    SoftReference<Byte[]> soft = new SoftReference<>(new Byte[SIZE1MB * 10], queue);
    WeakReference<Byte[]> weak = new WeakReference<>(new Byte[SIZE1KB], queue);
    PhantomReference<Byte[]> phantom = new PhantomReference<>(new Byte[SIZE1KB], queue);

    Reference<? extends Byte[]> collectedReference;
    // 初始状态
    log.info("初始值 强引用： {}", Arrays.hashCode(strong));
    log.info("初始值 软引用： {}", Arrays.hashCode(soft.get()));
    log.info("初始值 弱引用： {}", Arrays.hashCode(weak.get()));
    log.info("初始值 虚引用： {}", Arrays.hashCode(phantom.get()));
    do {
      collectedReference = queue.poll();
      log.info("初始值 引用队列： " + collectedReference);
    } while (collectedReference != null);
    log.info("********************");
    // 第一次手动触发GC
    System.gc();
    // 停100ms保证垃圾回收已经执行
    Thread.sleep(100);

    log.info("GC后 强引用： {}", Arrays.hashCode(strong));
    log.info("GC后 软引用： {}", Arrays.hashCode(soft.get()));
    log.info("GC后 弱引用： {}", Arrays.hashCode(weak.get()));
    log.info("GC后 虚引用： {}", Arrays.hashCode(phantom.get()));
    do {
      collectedReference = queue.poll();
      log.info("GC后 引用队列： " + collectedReference);
    }
    while (collectedReference != null);
    log.info("********************");

    // 再分配1M的内存，以模拟OOM的情况
    Byte[] newByte = new Byte[SIZE1MB * 15];

    log.info("Full GC后 强引用： {}", Arrays.hashCode(strong));
    log.info("Full GC后 软引用： {}", Arrays.hashCode(soft.get()));
    log.info("Full GC后 弱引用： {}", Arrays.hashCode(weak.get()));
    log.info("Full GC失败后 虚引用： {}", Arrays.hashCode(phantom.get()));
    do {
      collectedReference = queue.poll();
      log.info("Full GC失败后 引用队列： " + collectedReference);
    }
    while (collectedReference != null);
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
  }
}