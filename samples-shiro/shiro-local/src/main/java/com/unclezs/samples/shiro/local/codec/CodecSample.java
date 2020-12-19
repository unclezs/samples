package com.unclezs.samples.shiro.local.codec;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.CodecSupport;
import org.apache.shiro.codec.Hex;

/**
 * Shiro 提供的编码工具
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/19 14:24
 */
@Slf4j
public class CodecSample {
  public static void main(String[] args) {
    String original = "unclezs";
    String encode = Hex.encodeToString(original.getBytes());
    log.info("Hex编码：{}", encode);
    String decode = CodecSupport.toString((Hex.decode(encode)));
    log.info("Hexo解码：{}", decode);
    encode = Base64.encodeToString(original.getBytes());
    log.info("Base64编码：{}", encode);
    decode = Base64.decodeToString(encode);
    log.info("Base64解码：{}", decode);
  }
}
