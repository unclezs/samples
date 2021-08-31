package com.unclezs.kafka;

import com.unclezs.kafka.config.KafkaConfig;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author blog.unclezs.com
 * @date 2021/08/30
 */
public class ConsumerTest {
  private static KafkaConsumer<String, String> consumer;

  @BeforeClass
  public static void init() {
    Properties properties = KafkaConfig.consumerConfig();
    properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.BOOSTRAP_SERVER);
    consumer = new KafkaConsumer<>(properties);
  }

  @AfterClass
  public static void closeAll() {
    consumer.close();
  }

  @Test
  public void consumerMessage() {
    consumer.subscribe(Collections.singletonList(KafkaConfig.TOPIC_TEST));
    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
      for (ConsumerRecord<String, String> record : records)
        System.out.printf("partition%d: offset = %d, key = %s, value = %s%n", record.partition(), record.offset(),
            record.key(),
            record.value());
    }
  }
}
