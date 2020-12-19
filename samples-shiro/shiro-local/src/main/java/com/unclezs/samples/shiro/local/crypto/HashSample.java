package com.unclezs.samples.shiro.local.crypto;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

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
    // 说是简化，只觉得更麻烦了。。
    log.info("通过service方式：{}", hashByService("MD5", "hello", "123", 1));
  }

  private static String hashByService(String algorithm, String src, String privateSalt, int iterators) {
    DefaultHashService service = new DefaultHashService();
    service.setHashAlgorithmName(algorithm);
    // 私有盐
    service.setPrivateSalt(ByteSource.Util.bytes(privateSalt));
    // 自动生成公有盐
    service.setGeneratePublicSalt(true);
    // 公有盐生成器
    service.setRandomNumberGenerator(new SecureRandomNumberGenerator());
    // 迭代次数
    service.setHashIterations(iterators);
    HashRequest request = new HashRequest.Builder()
        .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes(src))
        .setSalt(ByteSource.Util.bytes(privateSalt)).setIterations(2).build();
    return service.computeHash(request).toHex();
  }
}
