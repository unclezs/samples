package com.unclezs.samples.proto.model;

import lombok.Data;

import java.util.List;

/**
 * @author blog.unclezs.com
 * @since 2022/6/16 11:04
 */
@Data
public class TokenDto {
  private String version;
  private String sign;
  private String secretKey;
  private List<DataDto> datas;
  private Integer num;
  private Long numLong;
  private Double numDouble;
}
