package com.unclezs.samples.shiro.local.crypto;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Shiro 的加密解密工具
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 14:27
 */
@Slf4j
public class Md5HashSample {
  public static void main(String[] args) {
    String original = "unclezs";
    String salt = "salt";
    Md5Hash md5Hash = new Md5Hash(original, salt);
    log.info("MD5加密后:{}", md5Hash.toBase64());
    log.info("MD5加密后:{}", md5Hash.toString());
  }
}
