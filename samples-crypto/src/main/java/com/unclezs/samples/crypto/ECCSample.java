package com.unclezs.samples.crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * @author blog.unclezs.com
 * @since 2022/5/17 11:42
 */
public class ECCSample {
  public static final String ALGORITHM = "EC";
  public static final String SING_ALGORITHM = "SHA1withECDSA";
  public static final String PROVIDER = "BC";
  public static final String ECIES = "ECIES";

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  public static void main(String[] args) throws Exception {
    SimpleKeyPair simpleKeyPair = generatePublicKeyAndPrivateKey();
    PrivateKey privateKey = parsePrivateKey(simpleKeyPair.getPrivateKey());
    PublicKey publicKey = parsePublicKey(simpleKeyPair.getPublicKey());
    String plainData = "mydata";
    // 签名
    String signed = sign(privateKey, plainData);
    System.out.println("签名: " + signed);
    boolean verifySuccess = verifySign(publicKey, plainData, signed);
    System.out.println("验证签名：" + verifySuccess);

    // 公钥加密 私钥解密
    byte[] encrypted = encrypt(publicKey, plainData);
    byte[] decrypted = decrypt(privateKey, encrypted);
    System.out.println(new String(decrypted));
  }

  /**
   * 生成公钥和私钥
   *
   * @throws NoSuchAlgorithmException 没有这样算法异常
   */
  public static SimpleKeyPair generatePublicKeyAndPrivateKey()
      throws NoSuchAlgorithmException, NoSuchProviderException {
    // 获取指定算法的密钥对生成器
    KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
    // 初始化密钥对生成器（指定密钥长度, 使用默认的安全随机数源）
    generator.initialize(256);
    // 随机生成一对密钥（包含公钥和私钥）
    KeyPair keyPair = generator.generateKeyPair();
    // 获取 公钥 和 私钥
    PublicKey pubKey = keyPair.getPublic();
    PrivateKey priKey = keyPair.getPrivate();
    System.out.println("私钥规范:" + priKey.getFormat());
    System.out.println("公钥规范:" + pubKey.getFormat());
    // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
    byte[] pubEncBytes = pubKey.getEncoded();
    byte[] priEncBytes = priKey.getEncoded();
    // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
    String pubEncBase64 = new BASE64Encoder().encode(pubEncBytes);
    String priEncBase64 = new BASE64Encoder().encode(priEncBytes);
    System.out.println("====公钥 开始=====");
    System.out.println(pubEncBase64);
    System.out.println("====公钥 结束=====");
    System.out.println("====私钥 开始=====");
    System.out.println(priEncBase64);
    System.out.println("====私钥 结束=====");
    return new SimpleKeyPair(pubEncBase64, priEncBase64);
  }

  /**
   * 签名
   *
   * @param key 关键
   * @return {@link String}
   * @throws Exception 异常
   */
  public static String sign(PrivateKey key, String original) throws Exception {
    Signature signature = Signature.getInstance(SING_ALGORITHM);
    signature.initSign(key);
    signature.update(original.getBytes());
    byte[] sign = signature.sign();
    return HexBin.encode(sign);
  }

  /**
   * 验证签名
   *
   * @param key  关键
   * @param sign 标志
   * @return boolean
   * @throws Exception 异常
   */
  public static boolean verifySign(PublicKey key, String original, String sign) throws Exception {
    Signature signature = Signature.getInstance(SING_ALGORITHM);
    signature.initVerify(key);
    signature.update(original.getBytes());
    return signature.verify(HexBin.decode(sign));
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
    return KeyFactory.getInstance(ALGORITHM, PROVIDER).generatePublic(encPubKeySpec);
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
    return KeyFactory.getInstance(ALGORITHM, PROVIDER).generatePrivate(encPubKeySpec);
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
    Cipher cipher = Cipher.getInstance(ECIES, PROVIDER);
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
    Cipher cipher = Cipher.getInstance(ECIES, PROVIDER);
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
