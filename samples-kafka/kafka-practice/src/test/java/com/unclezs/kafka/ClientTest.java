package com.unclezs.kafka;

import com.unclezs.kafka.consumer.KafkaConsumerClient;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author blog.unclezs.com
 * @date 2021/08/28
 */
public class ClientTest {
  public static KafkaConsumerClient client;

  @BeforeClass
  public static void init() {
    client = KafkaConsumerClient.builder()
//        .broker("localhost", 9092)
        .broker("localhost", 9093)
        .timeoutMs(10000)
        .partition(0)
        .topic("hello-topic")
        .build();
  }

  @Test
  public void testReadRecord() {
    String record = client.readRecord(0);
    System.out.println(record);
  }

  @Test
  public void testPrintAllRecord() {
    client.printAllRecord(0);
  }

  @Test
  public void testGetPartitionsInfo() {
    client.getPartitionsInfo().forEach(System.out::println);
  }

  @Test
  public void testGetLatestOffset() {
    System.out.println(
        KafkaConsumerClient.getOffsetLatest(client.getConsumer(), client.getTopic(), client.getPartition()));
  }
}
