package com.unclezs.samples.mapstruct.mapper;

import com.unclezs.samples.mapstruct.dto.PersonDto;
import com.unclezs.samples.mapstruct.model.Person;
import com.unclezs.samples.mapstruct.vo.PersonVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * mapstruct dto-model互转
 *
 * @author blog.unclezs.com
 * @since 2020/12/05 16:45
 */
@Mapper
public interface PersonMapper {
  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  @Mapping(source = "name", target = "cname")
  PersonDto personToPersonDto(Person user);

  @Mapping(source = "user",target = "guest")
  @Mapping(source = "name",target = "cname")
  PersonDto personToPersonDtoNested(Person person);

  @Mapping(source = "user.name",target = "userName")
  @Mapping(source = "user.age",target = "userAge")
  @Mapping(source = "name",target = "cname")
  PersonVo personToPersonVoNestedProperty(Person person);
}