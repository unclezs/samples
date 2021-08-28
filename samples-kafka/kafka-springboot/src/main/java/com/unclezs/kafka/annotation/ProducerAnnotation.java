package com.unclezs.kafka.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author blog.unclezs.com
 * @date 2021/08/27
 */
@Configuration
public class ProducerAnnotation {

  @KafkaListener(id = "unclezs", topics = "hello-topic")
  public void listen(String in) {
    System.out.println(in);
  }
}
