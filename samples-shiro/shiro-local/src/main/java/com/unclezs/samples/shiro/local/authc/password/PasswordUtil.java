package com.unclezs.samples.shiro.local.authc.password;

import lombok.experimental.UtilityClass;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 17:35
 */
@UtilityClass
public class PasswordUtil {
  public final int ITERATORS = 1;
  public final String ALGORITHM = "MD5";

  public static String encrypt(String src, String salt) {
    return new Md5Hash(src, salt, ITERATORS).toHex();
  }

  /**
   * 校验密码
   *
   * @param src  原始密码
   * @param hex  加密后的
   * @param salt 盐
   * @return 是否匹配
   */
  public static boolean validate(String src, String hex, String salt) {
    return encrypt(src, salt).equals(hex);
  }
}
