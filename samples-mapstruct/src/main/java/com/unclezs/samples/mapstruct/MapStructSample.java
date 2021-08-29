package com.unclezs.samples.mapstruct;

import com.unclezs.samples.mapstruct.dto.UserDto;
import com.unclezs.samples.mapstruct.mapper.UserMapper;
import com.unclezs.samples.mapstruct.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author blog.unclezs.com
 * @since 2020/12/05 16:09
 */
@Slf4j
public class MapStructSample {
  public static void main(String[] args) {
    User user = new User("123", "uncle");
    UserDto userDto = UserMapper.INSTANCE.userDto2User(user);
    log.info("{}", userDto);
  }
}
