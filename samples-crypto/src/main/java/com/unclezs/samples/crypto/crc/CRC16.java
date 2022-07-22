package com.unclezs.samples.crypto.crc;

import org.bouncycastle.util.encoders.Hex;

/**
 * CRC 即循环冗余校验码（Cyclic Redundancy Check[1] ）
 * <a href="https://datatracker.ietf.org/doc/html/rfc3385">RFC3385</a>
 *
 * @author blog.unclezs.com
 * @since 2022/7/22 10:12
 */
public class CRC16 {
  /**
   * @param bytes 字节
   * @return {@link String}
   */
  public static String getCrc(byte[] bytes) {
    int crc = 0xFFFF;
    int polynomial = 0xA001;

    for (byte aByte : bytes) {
      crc ^= aByte;
      for (int j = 0; j < Byte.SIZE; j++) {
        if ((crc & 0x01) == 1) {
          crc >>= 1;
          crc ^= polynomial;
        } else {
          crc >>= 1;
        }
      }
    }
    // 高低位转换，看情况使用
    crc = ((crc & 0xFF00) >> 8) | ((crc & 0xFF) << 8);
    return Integer.toHexString(crc);
  }

  public static void main(String[] args) {
    // 字符串转 16 进制 byte 数组
    String hexStr = Hex.toHexString("unclezs".getBytes());
    System.out.println("hexStr:" + hexStr);
    byte[] hexBytes = hexStr.getBytes();
    System.out.println(getCrc(hexBytes));
  }
}
