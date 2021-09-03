package com.unclezs.simples.jedis;

import cn.hutool.core.util.RandomUtil;
import com.unclezs.simples.redis.client.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.List;

/**
 * @author blog.unclezs.com
 * @date 2021/09/03
 */
@Slf4j
public class JedisTest {
  private static Jedis jedis;

  @BeforeClass
  public static void init() {
    jedis = RedisClient.getJedis();
  }


  @Test
  public void testSetStr() {
    jedis.set("uncle", "plus");
  }

  @Test
  public void testGetStr() {
    Assert.assertEquals(jedis.get("uncle"), "plus");
  }

  @Test
  public void testScan() {
    ScanParams scanParams = new ScanParams();
    scanParams.count(2);
    scanParams.match("uncle*");
    ScanResult<String> scanResult = jedis.scan("0", scanParams);
    String cursor = scanResult.getCursor();
    List<String> result = scanResult.getResult();
    log.info("cursor = {}", cursor);
    log.info("result = {}", result);
  }

  @Test
  public void setSubscribe() {
    jedis.subscribe(new JedisPubSub() {
      @Override
      public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
        log.info("onMessage channel = {} , message = {}", channel, message);
      }
    }, "uncle-test");
  }

  @Test
  public void setPublish() {
    jedis.publish("uncle-test", RandomUtil.randomString(10));
  }
}
