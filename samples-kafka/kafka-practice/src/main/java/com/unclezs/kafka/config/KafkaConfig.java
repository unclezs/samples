package com.unclezs.kafka.config;

import cn.hutool.core.util.RandomUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @author blog.unclezs.com
 * @date 2021/08/30
 */
public class KafkaConfig {
  public static final String BOOSTRAP_SERVER = "localhost:9092,localhost:9093";
  public static final String TOPIC_TEST = "hello-topic";

  public static Properties consumerConfig() {
    Properties props = new Properties();
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("bootstrap.servers", "localhost:9092,localhost:9093");
    props.put("max.poll.records", 10000);
    props.setProperty("group.id", "test");
    props.put("client.id", RandomUtil.randomNumbers(10));
    props.put("session.timeout.ms", 10000);
    props.put("enable.auto.commit", false);
    props.put("max.partition.fetch.bytes", 10 * 1024 * 1024);
    // 指定的 offset 无效时，默认直接抛出异常
    // props.put("auto.offset.reset", "none");
    return props;
  }


  public static Properties producerConfig() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092,localhost:9093");
    // 幂等性配置 如果程序自己能够保证幂等性时需要设置为false
    props.put("enable.idempotence", true);
    props.put("acks", "all");
    props.put("retries", 3);
    // 记录发送前 延迟毫秒数，如果设置1ms，那么在1ms内可能会存在多条消息记录在同一个请求中
    props.put("linger.ms", 1);
    // 超时时间
    props.put("request.timeout.ms", 1000);
    // 事物超时时间
    props.put("transaction.timeout.ms", 1000);
    // send超时
    props.put("max.block.ms", 1000);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    return props;
  }
}
