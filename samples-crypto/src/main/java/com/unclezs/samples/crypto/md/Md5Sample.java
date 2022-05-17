package com.unclezs.samples.crypto.md;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author blog.unclezs.com
 * @since 2022/5/17 11:52
 */
public class Md5Sample {
  public static void main(String[] args) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
    String original = "unclezs";
    byte[] result = messageDigest.digest(original.getBytes(StandardCharsets.UTF_8));
    System.out.println(original + " MD5加密后:" + HexBin.encode(result));
  }
}
