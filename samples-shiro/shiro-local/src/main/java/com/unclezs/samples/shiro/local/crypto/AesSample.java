package com.unclezs.samples.shiro.local.crypto;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;

import java.security.Key;

/**
 * Shiro 对称加密算法
 *
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 17:04
 */
@Slf4j
public class AesSample {
  public static void main(String[] args) {
    AesCipherService service = new AesCipherService();
    service.setKeySize(128);
    Key key = service.generateNewKey();
    String aesEncode = aesEncode(service, key, "uncle");
    log.info("对称加密后：{}", aesEncode);
    String aesDecode = aesDecode(service, key, aesEncode);
    log.info("对称解密后：{}", aesDecode);
  }

  private static String aesEncode(AesCipherService service, Key key, String src) {
    log.info("key:{}", key.getEncoded());
    return service.encrypt(src.getBytes(), key.getEncoded()).toHex();
  }

  private static String aesDecode(AesCipherService service, Key key, String src) {
    log.info("key:{}", key.getEncoded());
    return new String(service.decrypt(Hex.decode(src), key.getEncoded()).getBytes());
  }
}
