package com.unclezs.samples.proto.mapper;

import com.google.protobuf.Timestamp;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Date;

/**
 * @author blog.unclezs.com
 * @since 2022/6/17 14:41
 */
@MapperConfig(uses = Mappers.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class Mappers {
  /**
   * 转换为时间戳
   *
   * @param value 价值
   * @return {@link com.google.protobuf.Timestamp}
   */
  public static Timestamp map(Long value) {
    return value == null ?
        Timestamp.getDefaultInstance() :
        Timestamp.newBuilder().setSeconds(value / 1000).setNanos((int) ((value % 1000) * 1000000)).build();
  }

  /**
   * 从 Date 转  Timestamp
   *
   * @param value 时间毫秒数
   * @return Timestamp
   */
  public static Timestamp map(Date value) {
    if (value == null) {
      return Timestamp.getDefaultInstance();
    }
    return map(value.getTime());
  }

  /**
   * 转换为日期
   *
   * @param value 时间戳
   * @return {@link Date}
   */
  public static Date map(Timestamp value) {
    if (value == null) {
      return null;
    }
    return new Date(value.getSeconds() * 1000L);
  }

}
