package com.unclezs.samples.crypto;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author blog.unclezs.com
 * @since 2022/5/17 10:49
 */
public class RSASample {

  public static final String RSA = "RSA";

  public static void main(String[] args) throws Exception {
    SimpleKeyPair simpleKeyPair = generatePublicKeyAndPrivateKey();
    PrivateKey privateKey = parsePrivateKey(simpleKeyPair.getPrivateKey());
    PublicKey publicKey = parsePublicKey(simpleKeyPair.getPublicKey());
    // 公钥加密 私钥解密
    byte[] encrypted = encrypt(publicKey, "mydata");
    byte[] decrypted = decrypt(privateKey, encrypted);
    System.out.println(new String(decrypted));
    // 私钥加密 公钥解密
    encrypted = encrypt(privateKey, "mydata");
    decrypted = decrypt(publicKey, encrypted);
    System.out.println(new String(decrypted));
    testRsa();
  }

  private static void testRsa() {
    // 为了计算方便，p q 的值取小一旦，假设：p ＝ 17，q ＝ 19，
    int p = 17;
    int q = 19;
    // （1）求N：N ＝ p ＊ q ＝ 323；
    int n = 323;
    // （2）求L：L ＝ lcm（p－1， q－1）＝ lcm(16，18） ＝ 144，144为16和18对最小公倍数；
    int l = 144;
    // （3）求E：1 < E < L ，gcd（E，L）=1，即1 < E < 144，gcd（E，144） ＝ 1，E和144互为质数，E = 5显然满足上述2个条件，故E ＝ 5
    int e = 5;
    // （4）求D：求D也必须满足2个条件：1 < D < L，E＊D mod L ＝ 1，即1 < D < 144，5 ＊ D mod 144 ＝ 1，显然当D＝ 29 时满足上述两个条件。
    int d = 29;
    // （5）加密：准备的明文必须是小于N的数，因为加密或者解密都要 mod N，其结果必须小于N。
    // 假设明文 ＝ 123，则 密文＝（123的5次方）mod 323=225
    int original = 123;
    int secret = new BigInteger(original + "").pow(e).mod(new BigInteger(n + "")).intValue();
    // （6）解密：明文＝（225的29次方）mod 323 =123，所以解密后的明文为123。
    int decrypt = new BigInteger(secret + "").pow(d).mod(new BigInteger(n + "")).intValue();
    System.out.println("=====公钥加密私钥解密======");
    System.out.println(secret);
    System.out.println(decrypt);
    System.out.println(decrypt == original);

    secret = new BigInteger(original + "").pow(d).mod(new BigInteger(n + "")).intValue();
    decrypt = new BigInteger(secret + "").pow(e).mod(new BigInteger(n + "")).intValue();
    System.out.println("=====私钥加密公钥解密======");
    System.out.println(secret);
    System.out.println(decrypt);
    System.out.println(decrypt == original);
  }

  /**
   * 生成公钥和私钥
   *
   * @throws NoSuchAlgorithmException           没有这样算法异常
   * @throws InvalidAlgorithmParameterException 无效算法参数异常
   */
  public static SimpleKeyPair generatePublicKeyAndPrivateKey()
      throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
    // 获取指定算法的密钥对生成器
    KeyPairGenerator generator = KeyPairGenerator.getInstance(RSA);
    // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
    generator.initialize(new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4));
    // 随机生成一对密钥（包含公钥和私钥）
    KeyPair keyPair = generator.generateKeyPair();
    // 获取 公钥 和 私钥
    PublicKey pubKey = keyPair.getPublic();
    PrivateKey priKey = keyPair.getPrivate();
    System.out.println(priKey.getFormat());
    System.out.println(pubKey.getFormat());
    // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
    byte[] pubEncBytes = pubKey.getEncoded();
    byte[] priEncBytes = priKey.getEncoded();
    // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
    String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
    String priEncBase64 = new BASE64Encoder().encode(priEncBytes);
    return new SimpleKeyPair(pubEncBase64, priEncBase64);
  }

  /**
   * 解析公钥
   *
   * @param publicKeyBase64 公钥base64
   * @return {@link PublicKey}
   * @throws Exception 异常
   */
  public static PublicKey parsePublicKey(String publicKeyBase64) throws Exception {
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(publicKeyBase64);
    // 创建 已编码的公钥规格
    X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(keyBytes);
    // 获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
    return KeyFactory.getInstance(RSA).generatePublic(encPubKeySpec);
  }

  /**
   * 解析私钥
   *
   * @param privateKeyBase64 私钥base64
   * @return {@link PrivateKey}
   * @throws Exception 异常
   */
  public static PrivateKey parsePrivateKey(String privateKeyBase64) throws Exception {
    byte[] keyBytes = new BASE64Decoder().decodeBuffer(privateKeyBase64);
    // 创建 已编码的公钥规格
    PKCS8EncodedKeySpec encPubKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    // 获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
    return KeyFactory.getInstance(RSA).generatePrivate(encPubKeySpec);
  }

  /**
   * 加密
   *
   * @param key       key
   * @param plainData 简单数据
   * @return {@link byte[]}
   * @throws Exception 异常
   */
  public static byte[] encrypt(Key key, String plainData) throws Exception {
    // 获取指定算法的密码器
    Cipher cipher = Cipher.getInstance("RSA");
    // 初始化密码器
    cipher.init(Cipher.ENCRYPT_MODE, key);
    // 加密数据, 返回加密后的密文
    return cipher.doFinal(plainData.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * 解密
   *
   * @param key        key
   * @param cipherData 密码数据
   * @return {@link byte[]}
   * @throws Exception 异常
   */
  private static byte[] decrypt(Key key, byte[] cipherData) throws Exception {
    // 获取指定算法的密码器
    Cipher cipher = Cipher.getInstance("RSA");
    // 初始化密码器
    cipher.init(Cipher.DECRYPT_MODE, key);
    // 解密数据, 返回解密后的明文
    return cipher.doFinal(cipherData);
  }

  public static class SimpleKeyPair {
    private String publicKey;
    private String privateKey;

    public SimpleKeyPair(String publicKey, String privateKey) {
      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }

    public String getPublicKey() {
      return publicKey;
    }

    public void setPublicKey(String publicKey) {
      this.publicKey = publicKey;
    }

    public String getPrivateKey() {
      return privateKey;
    }

    public void setPrivateKey(String privateKey) {
      this.privateKey = privateKey;
    }
  }
}
