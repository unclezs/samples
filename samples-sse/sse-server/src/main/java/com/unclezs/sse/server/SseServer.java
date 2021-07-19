package com.unclezs.sse.server;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author blog.unclezs.com
 * @date 2021/07/17
 */
@RestController
@SpringBootApplication
public class SseServer implements ApplicationContextAware {
  private ApplicationContext context;


  public static void main(String[] args) {
    SpringApplication.run(SseServer.class, args);
  }

  @GetMapping(value = "/sse/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public ResponseEntity<SseEmitter> sseServer(@PathVariable(value = "id") String id) {
    // 防止nginx缓存请求
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("X-Accel-Buffering", "no");
    httpHeaders.setCacheControl(CacheControl.noCache());
    SseService manager = context.getBean(SseService.class);
    SseEmitter emitter = manager.registerSseEmitter(id);
    return ResponseEntity.ok().contentType(MediaType.TEXT_EVENT_STREAM).headers(httpHeaders).body(emitter);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.context = applicationContext;
    new Thread(() -> {
      while (true) {
        try {
          // 模拟推送消息
          context.getBean(SseService.class).sendMessage("123", String.format("sse消息：【%s】", DateUtil.now()));
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
