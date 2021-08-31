package com.unclezs.kafka.consumer;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.unclezs.kafka.common.BaseKafkaClient;
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
public class KafkaConsumerClient extends BaseKafkaClient {
  private KafkaConsumer<String, String> consumer;

  public KafkaConsumerClient(String brokers) {
    super(brokers);
    consumer = new KafkaConsumer<>(initConfig(true));
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

  public List<PartitionInfo> getPartitionsInfo(String topic) {
    return consumer.partitionsFor(topic);
  }


  public String readRecord(String topic, int partition, long targetOffset) {
    toOffset(topic, partition, targetOffset);
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

  public void printAllRecord(String topic, int partition, long targetOffset) {
    toOffset(topic, partition, targetOffset);
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
    records.forEach(record -> System.out.println(record.value()));
  }

  private void toOffset(String topic, int partition, long targetOffset) {
    TopicPartition topPartition = new TopicPartition(topic, partition);
    List<TopicPartition> partitions = new ArrayList<>();
    partitions.add(topPartition);
    consumer.assign(partitions);
    consumer.seek(topPartition, targetOffset);
  }

  public void close() {
    if (consumer != null) {
      consumer.close();
      consumer = null;
    }
  }
}
