package com.unclezs.samples.crypto.otp;

import cn.hutool.core.codec.Base32;
import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.lang.reflect.UndeclaredThrowableException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


/**
 * TOTP 规范实现 <a href="https://datatracker.ietf.org/doc/html/rfc6238">RFC6238</a>
 * <pre>
 * TOTP(K,C) = HOTP(K,C) = Truncate(HMAC-SHA-1(K,C))
 * </pre>
 *
 * @author blog.unclezs.com
 * @date 2022/5/18 1:58 PM
 */
public class TOTPSample {
  /**
   * 用户密钥长度 80bit
   */
  private static final int SECRET_KEY_BITS = 80;
  /**
   * TOTP 长度 : 6
   */
  private static final int CODE_DIGITS = 6;
  /**
   * 有效视窗长度 : (-2,2), 也就是服务器时间之间 可以早 1 分钟或者晚了 1 分钟
   */
  private static final int WINDOW_SIZE = 2;
  /**
   * TOTP更新周期 : 30s 更新一次
   */
  private static final int TIME_STEP = 30;
  /**
   * 伪随机函数生成算法 sha1 (pseudorandom number generator)
   */
  public static final String PRNG_ALGORITHM = "SHA1PRNG";
  /**
   * 加密算法 Hmac
   */
  public static final String CRYPTO_ALGORITHM = "HmacSHA1";
  /**
   * 二维码 url 格式
   **/
  public static final String QRCODE_URL = "otpauth://totp/%s:%s?secret=%s&issuer=%s";
  /**
   * C 的 byte 数
   */
  public static final int COUNTER_BITS = 8;

  /**
   * 创建密钥
   *
   * @return {@link String}
   */
  @SneakyThrows
  public String createSecretKey() {
    // 使用 SecureRandom 产生安全的随机数
    SecureRandom keyRandom = SecureRandom.getInstance(PRNG_ALGORITHM);
    byte[] keyBytes = keyRandom.generateSeed(SECRET_KEY_BITS / 8);
    // 将随机数进行 Base32 编码，产生一个随机字符串密钥
    return Base32.encode(keyBytes);
  }

  /**
   * HmacSha1算法对时间窗口(C)加密
   *
   * @param secretKeyByte   密钥字节
   * @param timeWindowBytes 时间窗字节
   * @return {@link byte[]}
   */
  private byte[] hmacSha1(byte[] secretKeyByte, byte[] timeWindowBytes) {
    SecretKeySpec keySpec = new SecretKeySpec(secretKeyByte, CRYPTO_ALGORITHM);
    try {
      // 使用 HmacSHA1 算法，返回一个 160 bit 的 hash 值
      Mac keyMac = Mac.getInstance(CRYPTO_ALGORITHM);
      keyMac.init(keySpec);
      return keyMac.doFinal(timeWindowBytes);
    } catch (GeneralSecurityException e) {
      e.printStackTrace();
      throw new UndeclaredThrowableException(e);
    }
  }

  /**
   * 获得8 字节的时间窗口(X)
   *
   * @param timeWindow 时间窗口(X)
   * @return {@link byte[]}
   */
  private byte[] getCounter(long timeWindow) {
    // 将 timeWindow 转为 byte 数组
    byte[] timeWindowBytes = new byte[COUNTER_BITS];
    for (int i = COUNTER_BITS; i-- > 0; timeWindow >>>= 8) {
      // 进行截断赋值
      timeWindowBytes[i] = (byte) timeWindow;
    }
    return timeWindowBytes;
  }

  /**
   * 获得时间窗口(X)
   *
   * @return long
   */
  private long getCurrentWindow() {
    return System.currentTimeMillis() / TimeUnit.SECONDS.toMillis(TIME_STEP);
  }

  /**
   * 生成 TOTP
   *
   * @param key 秘钥 (K)
   * @param c   计数器
   * @return {@link String}
   */
  public String generateOtp(String key, byte[] c) {
    byte[] keyBytes = Base32.decode(key);
    byte[] hash = hmacSha1(keyBytes, c);

    // offset : 开始取字节的位置; 由于 HmacSHA1算法返回的是160bit，也就是 20 byte， 所以 hash 长度是 20， 用hash
    // 的最后一位和 0xF 做 & 操作，使 0 <= offset <= 15, 这样即使 offset 为 15 ，连续 4
    // 次取字节，最多取到hash[18],不会发生数组越界
    int offset = hash[hash.length - 1] & 0xF;

    // 从 hash 中连续取出 4 个字节(32bit)，将其组成一个 int 型正整数, 进行了 4 次操作，分别是将 4个 字节移到 originOtp
    // 的第 1,2,3,4 字节
    // (hash[offset] & 0x7F) 则是为了 originOtp 的首位是 0， 可以得到一个正数
    int originOtp =
        ((hash[offset] & 0x7F) << 24) | ((hash[offset + 1] & 0xFF) << 16) | ((hash[offset + 2] & 0xFF) << 8) | (
            hash[offset + 3] & 0xFF);

    // google 中 codeDigits 为 6，表示得到的 totp 长度是 6
    // 对 10^6 取余，得到的余数的长度一定不大于 6
    int totp = originOtp % (int) Math.pow(10, CODE_DIGITS);

    // 如果得到的余数 totp 长度小于 6 ，则在前面补 0
    StringBuilder resultTotp = new StringBuilder(Integer.toString(totp));
    while (resultTotp.length() < CODE_DIGITS) {
      resultTotp.insert(0, "0");
    }
    return resultTotp.toString();
  }

  /**
   * 检查 TOTP
   *
   * @param secretKey  秘密密钥
   * @param targetTotp TOTP
   * @return boolean
   */
  public boolean checkTotp(String secretKey, String targetTotp) {
    long currentWindow = getCurrentWindow();
    for (int i = -WINDOW_SIZE; ++i < WINDOW_SIZE; ) {
      String totp = generateOtp(secretKey, getCounter(currentWindow + i));
      if (StrUtil.equals(targetTotp, totp)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 生成二维码
   *
   * @param secretKey 秘密密钥
   * @param username  用户名
   * @param issuer    发行人
   * @param prefix    前缀
   * @return {@link String}
   */
  public String generateQrCode(String secretKey, String username, String issuer, String prefix) {
    return String.format(QRCODE_URL, prefix, username, secretKey, issuer);
  }

  public static void main(String[] args) {
    TOTPSample authenticator = new TOTPSample();
    String secretKey = authenticator.createSecretKey();
    // secretKey = "RKJQRUEZC22LWHXK";
    String totp = authenticator.generateOtp(secretKey, authenticator.getCounter(authenticator.getCurrentWindow()));
    System.out.println(secretKey);
    System.out.println(totp);
    System.out.println(authenticator.checkTotp(secretKey, totp));
  }
}
