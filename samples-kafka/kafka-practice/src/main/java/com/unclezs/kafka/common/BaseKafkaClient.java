package com.unclezs.kafka.common;

import com.unclezs.kafka.config.KafkaConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.util.Properties;

/**
 * @author blog.unclezs.com
 * @date 2021/08/30
 */
@Setter
@Getter
public class BaseKafkaClient {
  private String brokers;

  public BaseKafkaClient(String brokers) {
    this.brokers = brokers;
  }

  public Properties initConfig(boolean consumer) {
    Properties properties = consumer ? KafkaConfig.consumerConfig() : KafkaConfig.producerConfig();
    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
    return properties;
  }
}
