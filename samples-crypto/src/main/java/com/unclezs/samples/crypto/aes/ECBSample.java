package com.unclezs.samples.crypto.aes;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author blog.unclezs.com
 * @since 2022/5/18 10:45
 */
public class ECBSample {
  public static void main(String[] args) throws Exception {
    // 原文
    String message = "Hello, world!";
    System.out.println("原文: " + message);
    // 128位密钥 = 16 bytes Key:
    byte[] key = "1234567890abcdef".getBytes(StandardCharsets.UTF_8);
    // 加密
    byte[] data = message.getBytes(StandardCharsets.UTF_8);
    byte[] encrypted = encrypt(key, data);
    System.out.println("加密: " + Base64.getEncoder().encodeToString(encrypted));
    // 解密
    byte[] decrypted = decrypt(key, encrypted);
    System.out.println("解密: " + new String(decrypted, StandardCharsets.UTF_8));
  }

  /**
   * 加密
   *
   * @param key   关键
   * @param input 输入
   * @return {@link byte[]}
   * @throws GeneralSecurityException 一般安全例外
   */
  public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    SecretKey keySpec = new SecretKeySpec(key, "AES");
    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    return cipher.doFinal(input);
  }

  /**
   * 解密
   *
   * @param key   关键
   * @param input 输入
   * @return {@link byte[]}
   * @throws GeneralSecurityException 一般安全例外
   */
  public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    SecretKey keySpec = new SecretKeySpec(key, "AES");
    cipher.init(Cipher.DECRYPT_MODE, keySpec);
    return cipher.doFinal(input);
  }
}
