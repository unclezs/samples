package com.unclezs.sse.client;

import java.io.InputStream;

public interface SseMessageHandler {

  void actMsg(InputStream is, String line);

}
