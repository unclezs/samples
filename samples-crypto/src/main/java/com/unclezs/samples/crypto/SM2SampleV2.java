package com.unclezs.samples.crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;
import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;

/**
 * @author blog.unclezs.com
 * @since 2022/5/17 19:04
 */
public class SM2SampleV2 {
  public static final String ALGORITHM = "EC";
  public static final String SING_ALGORITHM = "SHA1withECDSA";
  public static final String PROVIDER = "BC";
  public static final String ECIES = "ECIES";
  public static final String CRYPTO_NAME_SM2 = "sm2p256v1";
  public static final String SECURE_RANDOM_ALGORITHM = "SHA1PRNG";
  public static final String NOT_COMPRESS_FLG = "04";

  static {
    Security.addProvider(new BouncyCastleProvider());
  }

  public static void main(String[] args) throws Exception {
    SimpleKeyPair keyPair = generatePublicKeyAndPrivateKey();
    String publicKey = keyPair.getPublicKey();
    String privateKey = keyPair.getPrivateKey();
    String plainData = "mydata";
    // 公钥加密 私钥解密
    byte[] encrypted = encrypt(publicKey, plainData);
    String encryptedHex = HexBin.encode(encrypted);
    System.out.println("密文：" + encryptedHex);
    byte[] decrypted = decrypt(privateKey, encryptedHex);
    System.out.println("原文：" + new String(decrypted));
  }

  /**
   * 生成公钥和私钥
   *
   * @throws NoSuchAlgorithmException 没有这样算法异常
   */
  public static SimpleKeyPair generatePublicKeyAndPrivateKeyV1() throws Exception {
    // 获取一条SM2曲线参数
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    // 构造domain参数
    ECDomainParameters domainParameters =
        new ECDomainParameters(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN());
    // 1.创建密钥生成器
    KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER);
    // 2.初始化生成器,带上随机数
    generator.initialize(new ECParameterSpec(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN()));
    // 随机生成一对密钥（包含公钥和私钥）
    KeyPair keyPair = generator.generateKeyPair();
    // 获取 公钥 和 私钥
    // 公钥前面的 02 或者 03 表示是压缩公钥,04 表示未压缩公钥,04 的时候,可以去掉前面的 04
    ECPublicKeyParameters publicKeyParameters = (ECPublicKeyParameters) keyPair.getPublic();
    String publicKey = Hex.toHexString(publicKeyParameters.getQ().getEncoded(false));
    ECPrivateKeyParameters privateKeyParameters = (ECPrivateKeyParameters) keyPair.getPrivate();
    String privateKey = privateKeyParameters.getD().toString(16);
    System.out.println("====私钥 开始=====");
    System.out.println(privateKey);
    System.out.println("====私钥 结束=====");
    System.out.println("====公钥 开始=====");
    System.out.println(publicKey);
    System.out.println("====公钥 结束=====");
    return new SimpleKeyPair(publicKey, privateKey);
  }

  /**
   * 生成公钥和私钥
   *
   * @throws NoSuchAlgorithmException 没有这样算法异常
   */
  public static SimpleKeyPair generatePublicKeyAndPrivateKey() throws Exception {
    // 获取一条SM2曲线参数
    X9ECParameters sm2Parameters = GMNamedCurves.getByName(CRYPTO_NAME_SM2);
    // 构造domain参数
    ECDomainParameters domainParameters =
        new ECDomainParameters(sm2Parameters.getCurve(), sm2Parameters.getG(), sm2Parameters.getN());
    // 1.创建密钥生成器
    ECKeyPairGenerator generator = new ECKeyPairGenerator();
    // 2.初始化生成器,带上随机数
    generator.init(new ECKeyGenerationParameters(domainParameters, SecureRandom.getInstance(SECURE_RANDOM_ALGORITHM)));
    // 随机生成一对密钥（包含公钥和私钥）
    AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
    // 获取 公钥 和 私钥
    // 公钥前面的 02 或者 03 表示是压缩公钥,04 表示未压缩公钥,04 的时候,可以去掉前面的 04
    ECPublicKeyParameters publicKeyParameters = (ECPublicKeyParameters) keyPair.getPublic();
    String publicKey = Hex.toHexString(publicKeyParameters.getQ().getEncoded(false));
    ECPrivateKeyParameters privateKeyParameters = (ECPrivateKeyParameters) keyPair.getPrivate();
    String privateKey = privateKeyParameters.getD().toString(16);
    System.out.println("====私钥 开始=====");
    System.out.println(privateKey);
    System.out.println("====私钥 结束=====");
    System.out.println("====公钥 开始=====");
    System.out.println(publicKey);
    System.out.println("====公钥 结束=====");
    return new SimpleKeyPair(publicKey, privateKey);
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
