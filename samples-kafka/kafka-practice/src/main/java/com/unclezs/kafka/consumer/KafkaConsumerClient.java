package com.unclezs.kafka.consumer;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author blog.unclezs.com
 * @date 2021/08/28
 */
@Getter
@Slf4j
public class KafkaConsumerClient {
  private List<KafkaBrokerInfo> brokers = new ArrayList<>();
  private String topic;
  private int partition = 0;
  private KafkaConsumer<String, String> consumer;
  private String clientId;
  private KafkaBrokerInfo leadBroker;
  private int timeoutMs;

  public static KafkaConsumerClient builder() {
    return new KafkaConsumerClient();
  }

  /**
   * 获取指定partition最新的offset
   *
   * @param consumer    该partition的KafkaConsumer
   * @param partitionId partition的id
   * @return kafka这个partition这个时间点的offset
   */
  public static long getOffsetLatest(KafkaConsumer<String, String> consumer, String topic, int partitionId) {
    TopicPartition partition = new TopicPartition(topic, partitionId);
    List<TopicPartition> partitions = new ArrayList<>();
    partitions.add(partition);
    consumer.assign(partitions);
    consumer.seekToEnd(partitions);
    return consumer.position(partition);
  }

  public List<PartitionInfo> getPartitionsInfo() {
    return consumer.partitionsFor(topic);
  }

  public KafkaConsumerClient build() {
    consumer = new KafkaConsumer<>(generateConfig());
    return this;
  }

  public KafkaConsumerClient topic(String topic) {
    this.topic = topic;
    return this;
  }

  public KafkaConsumerClient partition(int partition) {
    this.partition = partition;
    return this;
  }

  public String readRecord(long targetOffset) {
    toOffset(targetOffset);
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
    for (ConsumerRecord<String, String> record : records) {
      long currentOffset = record.offset();
      if (currentOffset < targetOffset) {
        continue;
      }
      return record.value();
    }
    return null;
  }

  public void printAllRecord(long targetOffset) {
    toOffset(targetOffset);
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
    records.forEach(record -> System.out.println(record.value()));
  }

  private void toOffset(long targetOffset) {
    TopicPartition topPartition = new TopicPartition(topic, partition);
    List<TopicPartition> partitions = new ArrayList<>();
    partitions.add(topPartition);
    consumer.assign(partitions);
    consumer.seek(topPartition, targetOffset);
  }

  /**
   * todo 去重
   *
   * @param host 域名
   * @param port 端口
   * @return this
   */
  public KafkaConsumerClient broker(String host, int port) {
    KafkaBrokerInfo brokerInfo = new KafkaBrokerInfo(host, port);
    brokers.add(brokerInfo);
    return this;
  }

  public KafkaConsumerClient clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public KafkaConsumerClient timeoutMs(int timeoutMs) {
    this.timeoutMs = timeoutMs;
    return this;
  }

  private Properties generateConfig() {
    Properties properties = new Properties();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBrokerInfo.fromList(brokers));
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    if (StrUtil.isEmpty(clientId)) {
      clientId = RandomUtil.randomNumbers(10);
    }
    properties.put("client.id", clientId);
    properties.put("session.timeout.ms", timeoutMs);
    properties.put("enable.auto.commit", false);
    properties.put("max.partition.fetch.bytes", 10 * 1024 * 1024);
    properties.put("max.poll.records", 10000);
    // 指定的 offset 无效时，默认直接抛出异常
    properties.put("auto.offset.reset", "none");
    return properties;
  }

  public void close() {
    if (consumer != null) {
      consumer.close();
      consumer = null;
    }
  }
}
