package com.unclezs.kafka;

import cn.hutool.core.thread.ThreadUtil;
import com.unclezs.kafka.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author blog.unclezs.com
 * @date 2021/08/30
 */
@Slf4j
public class ProducerTest {
  private static KafkaProducer<String, String> producer;

  @BeforeClass
  public static void init() {
    Properties properties = KafkaConfig.producerConfig();
    properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, KafkaConfig.BOOSTRAP_SERVER);
    producer = new KafkaProducer<>(properties);
  }

  @AfterClass
  public static void closeAll() {
    producer.close();
  }

  @Test
  public void showTopicPartitions() {
    for (PartitionInfo info : producer.partitionsFor(KafkaConfig.TOPIC_TEST)) {
      System.out.println(info);
    }
  }

  /**
   * 普通的发送消息
   */
  @Test
  public void produceMessage() {
    for (int i = 0; i < 10; i++) {
      producer.send(new ProducerRecord<>(KafkaConfig.TOPIC_TEST, "unclezs" + i));
      ThreadUtil.sleep(1000);
    }
  }

  /**
   * 测试topic、partition不存在时
   * <p>
   * 结论：会等待max.block.ms配置的时间，如果topic、partition还未出在元素数据现则报错
   */
  @Test
  public void produceMessageOnNotExistPartition() {
    Future<RecordMetadata> future =
        producer.send(new ProducerRecord<>(KafkaConfig.TOPIC_TEST, 3, "key-unclezs", "value-unclezs"));
    try {
      RecordMetadata recordMetadata = future.get();
      System.out.println(recordMetadata);
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  /**
   * 测试事物
   */
  @Test
  public void testTransaction() {
    Properties props = KafkaConfig.producerConfig();
    // 事物配置
    props.put("transactional.id", "transactional_test");
    KafkaProducer<String, String> producer = new KafkaProducer<>(props);
    producer.initTransactions();
    try {
      producer.beginTransaction();
      for (int i = 0; i < 10; i++) {
        // do something
        producer.send(new ProducerRecord<>(KafkaConfig.TOPIC_TEST, "unclezs" + i));
      }
      producer.commitTransaction();
    } catch (Exception err) {
      log.error("produce message failed.", err);
      producer.abortTransaction();
    }
  }
}
