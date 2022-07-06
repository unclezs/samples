package com.unclezs.samples.proto;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.google.protobuf.Timestamp;
import com.unclezs.samples.proto.mapper.TokenMapper;
import com.unclezs.samples.proto.model.Data;
import com.unclezs.samples.proto.model.DemoEnum;
import com.unclezs.samples.proto.model.Token;
import com.unclezs.samples.proto.model.TokenDto;

/**
 * @author unclezs
 * @date 2022/6/16 11:10 AM
 */
public class Main {
  public static void main(String[] args) {
    Data data = Data.newBuilder()
        .setUsername("zhangsan")
        .setUrl("https://www.baidu.com")
        .build();
    Data data1 = Data.newBuilder()
        .setUsername("zhangsan")
        .setSerialNumber(664)
        .setUrl("https://www.baidu.com")
        .setDemoType(DemoEnum.ONE)
        .setUpdateTime(Timestamp.newBuilder().setSeconds(DateUtil.currentSeconds()).build())
        .build();
    Token token = Token.newBuilder()
        .addAllDatas(ListUtil.of(data, data1))
        .setSecretKey("123")
        .setVersion(1)
        .setSign("222")
        .build();

    TokenDto dto = TokenMapper.ME.map(token);
    System.out.println(JSONUtil.toJsonPrettyStr(dto));

    dto.setSign(null);
    Token tToken = TokenMapper.ME.map(dto);
    System.out.println(tToken);
  }
}