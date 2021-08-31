package com.unclezs.kafka.producer;

import com.unclezs.kafka.common.BaseKafkaClient;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @author blog.unclezs.com
 * @date 2021/08/28
 */
@Getter
@Slf4j
public class KafkaProducerClient extends BaseKafkaClient {
  private KafkaProducer<String, String> producer;

  public KafkaProducerClient(String brokers) {
    super(brokers);
    producer = new KafkaProducer<>(initConfig(false));
  }

  public void close() {
    if (producer != null) {
      producer.close();
      producer = null;
    }
  }
}
