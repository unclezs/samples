package com.unclezs.samples.proto.mapper;

import com.unclezs.samples.proto.model.Token;
import com.unclezs.samples.proto.model.TokenDto;
import org.mapstruct.Mapper;

/**
 * @author blog.unclezs.com
 * @since 2022/6/16 11:02
 */
@Mapper(config = Mappers.class)
public interface TokenMapper {
  TokenMapper ME = org.mapstruct.factory.Mappers.getMapper(TokenMapper.class);

  /**
   * 映射
   *
   * @param tokenDto 用户
   * @return {@link Token}
   */
  Token map(TokenDto tokenDto);

  /**
   * 映射
   *
   * @param token 用户dto
   * @return {@link TokenDto}
   */
  TokenDto map(Token token);
}
