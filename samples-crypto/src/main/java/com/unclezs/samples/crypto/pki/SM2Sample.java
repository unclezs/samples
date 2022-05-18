package com.unclezs.samples.crypto.pki;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.util.Base64;

/**
 * @author blog.unclezs.com
 * @since 2022/5/17 19:04
 */
public class SM2Sample {
  public static final String ALGORITHM = "EC";
  public static final String SING_ALGORITHM = "SM3withSM2";
  public static final String PROVIDER = "BC";
  public static final String CRYPTO_NAME_SM2 = "sm2p256v1";
  public static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
  public static final String NOT_COMPRESS_FLG = "04";

  static {
    if (Security.getProvider(PROVIDER) == null) {
      Security.addProvider(new BouncyCastleProvider());
    }
  }

  public static void main(String[] args) throws Exception {
    SimpleKeyPair simpleKeyPair = generatePublicKeyAndPrivateKey();
    PrivateKey privateKey = parsePrivateKey(simpleKeyPair.getPrivateKey());
    PublicKey publicKey = parsePublicKey(simpleKeyPair.getPublicKey());
    String plainData = "{\"username\":\"zhangsan\",\"secretKey\":\"2AES7T4TOH57QXUP\",\"serialNumber\":\"664\",\"totp\":1}";
    // 签名
    String signed = sign(privateKey, plainData);
    System.out.println("签名: " + signed);
    boolean verifySuccess = verifySign(publicKey, plainData, signed);
    System.out.println("验证签名：" + verifySuccess);

    // 公钥加密 私钥解密
    byte[] encrypted = encrypt(simpleKeyPair.getPublicKey(), plainData);
    String encryptedHex = HexBin.encode(encrypted);
    System.out.println("密文：" + encryptedHex);
    System.out.println("密文base64：" + Base64.getEncoder().encodeToString(encrypted));
    byte[] decrypted = decrypt(simpleKeyPair.getPrivateKey(), encryptedHex);
    System.out.println("原文：" + new String(decrypted));
  }

  /**
   * 生成公钥和私钥
   *
   * @throws java.security.NoSuchAlgorithmException 没有这样算法异常
   */
  public static SimpleKeyPair generatePublicKeyAndPrivateKey() throws Exception {
    // 获取一条SM2曲线参数
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    // 1.创建密钥生成器
    KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
    // 2.初始化生成器,带上随机数
    generator.initialize(new ECParameterSpec(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN()));
    // 随机生成一对密钥（包含公钥和私钥）
    KeyPair keyPair = generator.generateKeyPair();
    // 获取 公钥 和 私钥
    PublicKey pubKey = keyPair.getPublic();
    PrivateKey priKey = keyPair.getPrivate();
    System.out.println("私钥规范:" + priKey.getFormat());
    System.out.println("公钥规范:" + pubKey.getFormat());
    // 获取 公钥和私钥 的 编码格式（通过该 编码格式 可以反过来 生成公钥和私钥对象）
    String pubEncHex = Hex.toHexString(((BCECPublicKey) keyPair.getPublic()).getQ().getEncoded(false));
    String priEncHex = ((BCECPrivateKey) keyPair.getPrivate()).getD().toString(16);
    // 把 公钥和私钥 的 编码格式 转换为 Base64文本 方便保存
    System.out.println("====公钥 开始=====");
    System.out.println(pubEncHex);
    System.out.println("====公钥 结束=====");
    System.out.println("====私钥 开始=====");
    System.out.println(priEncHex);
    System.out.println("====私钥 结束=====");
    return new SimpleKeyPair(pubEncHex, priEncHex);
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
   * @param publicKeyHex q hex
   * @return {@link PublicKey}
   * @throws Exception 异常
   */
  public static PublicKey parsePublicKey(String publicKeyHex) throws Exception {
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    ECParameterSpec ecParameterSpec =
        new ECParameterSpec(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN());
    // 创建 已编码的公钥规格
    ECPublicKeySpec publicKeySpec =
        new ECPublicKeySpec(sm2Parameters.getCurve().decodePoint(Hex.decode(publicKeyHex)), ecParameterSpec);
    // 获取指定算法的密钥工厂, 根据 已编码的公钥规格, 生成公钥对象
    return new BCECPublicKey(ALGORITHM, publicKeySpec, BouncyCastleProvider.CONFIGURATION);
  }

  /**
   * 解析私钥
   *
   * @param privateKeyHex 私钥base64
   * @return {@link PrivateKey}
   * @throws Exception 异常
   */
  public static PrivateKey parsePrivateKey(String privateKeyHex) throws Exception {
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    ECParameterSpec ecParameterSpec =
        new ECParameterSpec(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN());
    BigInteger d = new BigInteger(privateKeyHex, 16);
    ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(d, ecParameterSpec);
    return new BCECPrivateKey(ALGORITHM, privateKeySpec, BouncyCastleProvider.CONFIGURATION);
  }

  /**
   * 加密
   *
   * @param key       key
   * @param plainData 简单数据
   * @return {@link byte[]}
   * @throws Exception 异常
   */
  public static byte[] encrypt(String key, String plainData) throws Exception {
    // 获取一条SM2曲线参数
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    // 构造 ECC 算法参数，曲线方程、椭圆曲线 G 点、大整数 N
    ECDomainParameters domainParameters =
        new ECDomainParameters(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN());
    // 提取公钥点
    ECPoint pukPoint = sm2Parameters.getCurve().decodePoint(Hex.decode(key));
    // 公钥前面的 02 或者 03 表示是压缩公钥，04 表示未压缩公钥, 04 的时候，可以去掉前面的04
    ECPublicKeyParameters publicKeyParameters = new ECPublicKeyParameters(pukPoint, domainParameters);
    // C1C3C2 为国密标准
    SM2Engine engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
    // 设置sm2为加密模式
    engine.init(true, new ParametersWithRandom(publicKeyParameters, SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM)));
    byte[] in = plainData.getBytes();
    return engine.processBlock(in, 0, in.length);
  }

  /**
   * 解密
   *
   * @param key        key
   * @param cipherData 密码数据 HEX
   * @return {@link byte[]}
   * @throws Exception 异常
   */
  private static byte[] decrypt(String key, String cipherData) throws Exception {
    // 使用BC库加解密时密文以 04 开头，传入的密文前面没有 04 则补上
    if (!cipherData.startsWith(NOT_COMPRESS_FLG)) {
      cipherData = NOT_COMPRESS_FLG + cipherData;
    }
    byte[] cipherDataByte = Hex.decode(cipherData);
    // 获取一条SM2曲线参数
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    // 构造domain参数
    ECDomainParameters domainParameters =
        new ECDomainParameters(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN());
    BigInteger privateKeyD = new BigInteger(key, 16);
    ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);
    // C1C3C2 为国密标准
    SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
    // 设置sm2为解密模式
    sm2Engine.init(false, privateKeyParameters);
    return sm2Engine.processBlock(cipherDataByte, 0, cipherDataByte.length);
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
