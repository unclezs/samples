package com.unclezs.samples.crypto.aes;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.codec.Base64;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author blog.unclezs.com
 * @since 2022/5/18 10:47
 */
public class CBCSample {
  public static void main(String[] args) throws Exception {
    // 原文
    String message = "{\"username\":\"zhangsan\",\"secretKey\":\"2AES7T4TOH57QXUP\"}";
    System.out.println("原文: " + message);
    // 256位密钥 = 32 bytes Key:
    byte[] key = "1234567890abcdef1234567890abcdef".getBytes(StandardCharsets.UTF_8);
    // 加密
    byte[] data = message.getBytes(StandardCharsets.UTF_8);
    byte[] encrypted = encrypt(key, data);
    System.out.println("密文base64: " + Base64.encode(encrypted));
    System.out.println("密文base32: " + Base32.encode(encrypted));
    // 解密
    byte[] decrypted = decrypt(key, encrypted);
    System.out.println("原文: " + new String(decrypted, StandardCharsets.UTF_8));
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
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
    // CBC模式需要生成一个16 bytes的initialization vector:
    SecureRandom sr = SecureRandom.getInstanceStrong();
    byte[] iv = sr.generateSeed(16);
    IvParameterSpec ivps = new IvParameterSpec(iv);
    cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivps);
    byte[] data = cipher.doFinal(input);
    // IV不需要保密，把IV和密文一起返回:
    return join(iv, data);
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
    // 把input分割成IV和密文:
    byte[] iv = new byte[16];
    byte[] data = new byte[input.length - 16];
    System.arraycopy(input, 0, iv, 0, 16);
    System.arraycopy(input, 16, data, 0, data.length);
    // 解密:
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
    IvParameterSpec ivps = new IvParameterSpec(iv);
    cipher.init(Cipher.DECRYPT_MODE, keySpec, ivps);
    return cipher.doFinal(data);
  }

  public static byte[] join(byte[] bs1, byte[] bs2) {
    byte[] r = new byte[bs1.length + bs2.length];
    System.arraycopy(bs1, 0, r, 0, bs1.length);
    System.arraycopy(bs2, 0, r, bs1.length, bs2.length);
    return r;
  }
}
