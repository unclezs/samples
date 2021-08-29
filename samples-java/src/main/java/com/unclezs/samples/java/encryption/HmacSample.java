package com.unclezs.samples.java.encryption;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * HmacSha256算法加密
 * https://blog.csdn.net/sdnyqfyqf/article/details/105534376
 *
 * @author blog.unclezs.com
 * @since 2020/12/19 16:33
 */
@Slf4j
public class HmacSample {
  public static void main(String[] args) {
    log.info("HmacSHA256 加密后：{}", byHmacSha256("salt", "unclezs"));
  }

  private static byte[] byHmacSha256(String salt, String original) {
    String hmacSha256 = "HmacSHA256";
    try {
      Mac mac = Mac.getInstance(hmacSha256);
      SecretKey key = new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), hmacSha256);
      mac.init(key);
      return mac.doFinal(original.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      log.error("加密算法不存在：{}", hmacSha256, e);
    } catch (InvalidKeyException e) {
      log.error("非法私有key：{}", hmacSha256, e);
    }
    return new byte[0];
  }
}
