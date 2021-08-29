package com.unclezs.kafka.controller;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author blog.unclezs.com
 * @date 2021/08/26
 */
@RequestMapping
@RestController
@AllArgsConstructor
public class AppController {
  private final KafkaTemplate<String, String> template;

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("send")
  public String send(String topic, String message) {
    template.send(topic, message);
    return message;
  }
}
