package com.unclezs.sse.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.sse.RealEventSource;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.concurrent.TimeUnit;

/**
 * @author blog.unclezs.com
 * @date 2021/07/17
 */
public class OkSseClient {
  public static void main(String[] args) {
    // 定义see接口
    Request request = new Request.Builder().url("http://127.0.0.1:8080/sse/123").build();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.DAYS)
        .readTimeout(1, TimeUnit.DAYS)//这边需要将超时显示设置长一点，不然刚连上就断开，之前以为调用方式错误被坑了半天
        .build();

    // 实例化EventSource，注册EventSource监听器
    RealEventSource realEventSource = new RealEventSource(request, new EventSourceListener() {
      private long callStartNanos;

      private void printEvent(String name) {
        long nowNanos = System.nanoTime();
        if (name.equals("callStart")) {
          callStartNanos = nowNanos;
        }
        long elapsedNanos = nowNanos - callStartNanos;
        System.out.printf("=====> %.3f %s%n", elapsedNanos / 1000000000d, name);
      }

      @Override
      public void onOpen(EventSource eventSource, Response response) {
        printEvent("onOpen");
      }

      @Override
      public void onEvent(EventSource eventSource, String id, String type, String data) {
        printEvent("onEvent");
        System.out.println(data);//请求到的数据
      }

      @Override
      public void onClosed(EventSource eventSource) {
        printEvent("onClosed");
      }

      @Override
      public void onFailure(EventSource eventSource, Throwable t, Response response) {
        t.printStackTrace();
        printEvent("onFailure");//这边可以监听并重新打开
      }
    });
    realEventSource.connect(okHttpClient);//真正开始请求的一步
  }
}
