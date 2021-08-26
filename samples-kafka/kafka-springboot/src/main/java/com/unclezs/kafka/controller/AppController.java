package com.unclezs.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author blog.unclezs.com
 * @date 2021/08/26
 */
@RequestMapping
@RestController
public class AppController {

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }
}
