package com.unclezs.samples.http.httpclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author blog.unclezs.com
 * @date 2020/12/20 5:26 下午
 */
@Slf4j
public class HttpSample {
  public static void main(String[] args) throws IOException {
    Content content = Request.get("https://www.bxwxorg.com/read/87912/")
        .execute().returnContent();
    System.out.println(content.asString(Charset.defaultCharset()));
  }
}
