package com.unclezs.sse.server;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author blog.unclezs.com
 * @date 2021/07/17
 */
@Service
public class SseEmitterManager implements SseService {
  private static final Map<String, SseEmitter> SSE_EMITTERS = new HashMap<>();

  public SseEmitter registerSseEmitter(String id) {
    SseEmitter emitter = new SseEmitter(60L * 1000L);
    emitter.onCompletion(() -> System.out.println("SseEmitter is completed"));
    emitter.onTimeout(() -> System.out.println("SseEmitter is timed out"));
    emitter.onError((ex) -> System.out.println("SseEmitter got error:" + ex.getMessage()));
    SSE_EMITTERS.put(id, emitter);
    return emitter;
  }

  @Override
  public void sendMessage(String id, Object data) {
    SseEmitter emitter = SSE_EMITTERS.get(id);
    if (emitter == null) {
      return;
    }
    System.out.println("发送消息：" + id + " data: " + data);
    try {
      emitter.send(data);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
