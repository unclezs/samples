package com.unclezs.samples.mapstruct.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhanghongguo@sensorsdata.cn
 * @since 2020/12/05 18:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVo {
  private String cname;
  private String userName;
  private String userAge;
}
