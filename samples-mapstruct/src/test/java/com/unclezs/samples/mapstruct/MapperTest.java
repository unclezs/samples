package com.unclezs.samples.mapstruct;

import com.unclezs.samples.mapstruct.dto.PersonDto;
import com.unclezs.samples.mapstruct.dto.UserDto;
import com.unclezs.samples.mapstruct.mapper.PersonMapper;
import com.unclezs.samples.mapstruct.mapper.UserMapper;
import com.unclezs.samples.mapstruct.model.Person;
import com.unclezs.samples.mapstruct.model.User;
import com.unclezs.samples.mapstruct.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author blog.unclezs.com
 * @since 2020/12/05 18:43
 */
@Slf4j
public class MapperTest {
  @Test
  public void testSamePropertyName() {
    User user = new User("123", "uncle");
    UserDto userDto = UserMapper.INSTANCE.userDto2User(user);
    Assert.assertEquals("123", userDto.getAge());
    log.info("{}", userDto);
  }

  @Test
  public void TestDiffPropertyName() {
    Person person = new Person();
    person.setName("uncle");
    PersonDto personDto = PersonMapper.INSTANCE.personToPersonDto(person);
    Assert.assertEquals("uncle", personDto.getCname());
  }

  @Test
  public void testNested() {
    Person person = new Person();
    person.setName("uncle");
    User user = new User("123", "uncle");
    person.setUser(user);
    PersonDto personDto = PersonMapper.INSTANCE.personToPersonDtoNested(person);
    Assert.assertEquals("uncle", personDto.getCname());
    Assert.assertEquals("uncle", personDto.getGuest().getName());
  }

  @Test
  public void testNestedToProperty() {
    Person person = new Person();
    person.setName("uncle");
    User user = new User("123", "uncle");
    person.setUser(user);
    PersonVo personVo = PersonMapper.INSTANCE.personToPersonVoNestedProperty(person);
    Assert.assertEquals("uncle", personVo.getCname());
    Assert.assertEquals("uncle", personVo.getUserName());
  }
}
