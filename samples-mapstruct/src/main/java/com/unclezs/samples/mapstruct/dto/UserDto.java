package com.unclezs.samples.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/05 16:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  private String age;
  private String name;
}
