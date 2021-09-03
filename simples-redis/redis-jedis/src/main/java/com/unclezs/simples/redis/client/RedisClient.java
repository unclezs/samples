package com.unclezs.simples.redis.client;

import lombok.Getter;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

/**
 * @author blog.unclezs.com
 * @date 2021/09/03
 */
public class RedisClient {
  @Getter
  private static Jedis jedis;

  static {
    init();
  }

  public static void init() {
    DefaultJedisClientConfig config = DefaultJedisClientConfig.builder()
        .database(1)
        .clientName("uncle-test")
        .password("uncle")
        .build();
    jedis = new Jedis(new HostAndPort("localhost", 6379), config);
  }
}
