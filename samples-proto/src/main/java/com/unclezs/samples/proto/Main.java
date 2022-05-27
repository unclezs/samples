package com.unclezs.samples.proto;

import cn.hutool.crypto.digest.MD5;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.unclezs.samples.proto.model.Data;
import com.unclezs.samples.proto.model.Token;

import java.util.Base64;

public class Main {
  public static void main(String[] args) throws InvalidProtocolBufferException {
    Data data = Data.newBuilder()
        .setUsername("zhangsan")
        .setSerialNumber(664)
        .setUrl("https://www.baidu.com")
        .build();
    Token token = Token.newBuilder()
        .setData(data.toByteString())
        .setSecretKey(ByteString.copyFromUtf8("123"))
        .setVersion(1)
        .setSign(ByteString.copyFrom(MD5.create().digest(data.toByteArray())))
        .build();
    byte[] tokenBytes = token.toByteArray();
    // base64 编码得到 token
    String tokenStr = Base64.getEncoder().encodeToString(tokenBytes);
    // 解析
    Token theToken = Token.parseFrom(Base64.getDecoder().decode(tokenStr));
    System.out.println(theToken.getSign());
  }
}