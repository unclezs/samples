package com.unclezs.kafka.producer;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.unclezs.kafka.consumer.KafkaBrokerInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
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
public class KafkaProducerClient {
  private List<KafkaBrokerInfo> brokers = new ArrayList<>();
  private String topic;
  private int partition = 0;
  private KafkaProducer<String, String> producer;
  private String clientId;
  private KafkaBrokerInfo leadBroker;
  private int timeoutMs;

  public static KafkaProducerClient builder() {
    return new KafkaProducerClient();
  }


  public KafkaProducerClient build() {
    producer = new KafkaProducer<>(generateConfig());
    return this;
  }

  public KafkaProducerClient topic(String topic) {
    this.topic = topic;
    return this;
  }

  public KafkaProducerClient partition(int partition) {
    this.partition = partition;
    return this;
  }

  /**
   * todo 去重
   *
   * @param host 域名
   * @param port 端口
   * @return this
   */
  public KafkaProducerClient broker(String host, int port) {
    KafkaBrokerInfo brokerInfo = new KafkaBrokerInfo(host, port);
    brokers.add(brokerInfo);
    return this;
  }

  public KafkaProducerClient clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public KafkaProducerClient timeoutMs(int timeoutMs) {
    this.timeoutMs = timeoutMs;
    return this;
  }

  private Properties generateConfig() {
    Properties properties = new Properties();
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        "org.apache.kafka.common.serialization.StringDeserializer");
    if (StrUtil.isEmpty(clientId)) {
      clientId = RandomUtil.randomNumbers(10);
    }
    properties.put("client.id", clientId);
    properties.put("session.timeout.ms", timeoutMs);
    properties.put("enable.auto.commit", false);
    // 指定的 offset 无效时，默认直接抛出异常
    properties.put("auto.offset.reset", "none");
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaBrokerInfo.fromList(brokers));
    return properties;
  }

  public void close() {
    if (producer != null) {
      producer.close();
      producer = null;
    }
  }
}
