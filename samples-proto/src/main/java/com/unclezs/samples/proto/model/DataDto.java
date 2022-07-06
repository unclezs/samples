package com.unclezs.samples.proto.model;

import lombok.Data;

import java.util.Date;

/**
 * @author blog.unclezs.com
 * @since 2022/6/16 11:05
 */
@Data
public class DataDto {
  private String username;
  private Integer serialNumber;
  private String url;
  private Date updateTime;
  private DemoEnumDto demoType;
}