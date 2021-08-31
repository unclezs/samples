package com.unclezs.kafka;

import com.unclezs.kafka.consumer.KafkaConsumerClient;
import com.unclezs.kafka.producer.KafkaProducerClient;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

/**
 * @author blog.unclezs.com
 * @date 2021/08/28
 */
public class ClientTest {
  public static final String HELLO_TOPIC = "hello-topic";
  public static KafkaConsumerClient consumerClient;
  public static KafkaProducerClient producerClient;

  @BeforeClass
  public static void init() {
    consumerClient = new KafkaConsumerClient("localhost:9092,localhost:9093");
    producerClient = new KafkaProducerClient("localhost:9092,localhost:9093");
  }

  @Test
  public void testReadRecord() {
    String record = consumerClient.readRecord(HELLO_TOPIC, 0, 0);
    System.out.println(record);
  }

  @Test
  public void testConsumer() {
    consumerClient.getConsumer().subscribe(Collections.singleton(HELLO_TOPIC));
  }

  @Test
  public void testPrintAllRecord() {
    consumerClient.printAllRecord(HELLO_TOPIC, 0, 0);
  }

  @Test
  public void testGetPartitionsInfo() {
    consumerClient.getPartitionsInfo(HELLO_TOPIC).forEach(System.out::println);
  }

  @Test
  public void testGetLatestOffset() {
    System.out.println(KafkaConsumerClient.getOffsetLatest(consumerClient.getConsumer(), HELLO_TOPIC, 0));
  }

  @Test
  public void testProduceMessage() {

  }
}
