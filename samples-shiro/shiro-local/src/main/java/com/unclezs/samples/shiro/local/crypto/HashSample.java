package com.unclezs.samples.shiro.local.crypto;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Shiro 支持的hash算法
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 14:35
 */
@Slf4j
public class HashSample {
  public static void main(String[] args) {
    String[] algorithms = {"MD2", "MD5", "SHA1", "SHA-256", "SHA-384", "SHA-512"};
    for (String algorithm : algorithms) {
      SimpleHash hash = new SimpleHash(algorithm, "source", "salt", 1);
      log.info("{}散列算法加密后: {}.", algorithm, hash.toString());
    }
  }
}
