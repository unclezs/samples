package com.unclezs.kafka.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * 配置 partitioner.class 自定义分区策略
 *
 * @author blog.unclezs.com
 * @date 2021/09/01
 */
public class CustomPartitioner implements Partitioner {
  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
    return 0;
  }

  @Override
  public void close() {
  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}
