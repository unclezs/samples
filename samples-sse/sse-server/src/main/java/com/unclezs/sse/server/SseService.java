package com.unclezs.sse.server;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author blog.unclezs.com
 * @date 2021/07/17
 */
public interface SseService {

  /**
   * 注册sse emitter
   *
   * @param id 唯一标识符
   */
  SseEmitter registerSseEmitter(String id);

  void sendMessage(String id, Object data);
}
