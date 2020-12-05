package com.unclezs.samples.mapstruct.dto;

import com.unclezs.samples.mapstruct.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author blog.unclezs.com
 * @since 2020/12/05 18:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
  private String cname;
  private User guest;
}
