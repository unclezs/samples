package com.unclezs.samples.java.encryption;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Java的摘要算法
 *
 * @author blog.unclezs.com
 * @since 2020/12/19 14:48
 */
@Slf4j
public class MessageDigestSample {
  /**
   * 常用的摘要算法
   */
  public static final String DIGEST_MD2 = "MD2";
  public static final String DIGEST_MD5 = "MD5";
  public static final String DIGEST_SHA = "SHA";
  public static final String DIGEST_SHA_224 = "SHA-224";
  public static final String DIGEST_SHA_256 = "SHA-256";
  public static final String DIGEST_SHA_384 = "SHA-384";
  public static final String DIGEST_SHA_512 = "SHA-512";

  public static void main(String[] args) {
    String[] algorithms =
        {DIGEST_MD2, DIGEST_MD5, DIGEST_SHA, DIGEST_SHA_224, DIGEST_SHA_256, DIGEST_SHA_384, DIGEST_SHA_512};
    String original = "original";
    String salt = "salt";
    for (String algorithm : algorithms) {
      log.info("{}生成的摘要：{}", algorithm, getDigest(algorithm, original, salt));
    }
  }

  private static byte[] getDigest(String algorithm, String original, String salt) {
    byte[] hashed = new byte[0];
    try {
      MessageDigest digest = MessageDigest.getInstance(algorithm);
      digest.reset();
      digest.update(salt.getBytes(StandardCharsets.UTF_8));
      hashed = digest.digest(original.getBytes());
    } catch (NoSuchAlgorithmException e) {
      log.error("摘要算法不存在:{}", algorithm, e);
    }
    return hashed;
  }
}
