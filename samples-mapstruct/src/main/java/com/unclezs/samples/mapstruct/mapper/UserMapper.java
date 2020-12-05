package com.unclezs.samples.mapstruct.mapper;

import com.unclezs.samples.mapstruct.dto.UserDto;
import com.unclezs.samples.mapstruct.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * mapstruct dto-model互转
 *
 * @author blog.unclezs.com
 * @since 2020/12/05 16:45
 */
@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  User userDto2User(UserDto userDto);

  UserDto userDto2User(User user);

  void updateUserFromDto(@MappingTarget User user,UserDto userDto);
}