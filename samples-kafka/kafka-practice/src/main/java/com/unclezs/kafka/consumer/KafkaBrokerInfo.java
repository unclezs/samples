package com.unclezs.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author blog.unclezs.com
 * @date 2021/08/28
 */
@Getter
@AllArgsConstructor
public class KafkaBrokerInfo {
  String host;
  int port;

  public static String fromList(List<KafkaBrokerInfo> brokerInfos) {
    StringBuilder builder = new StringBuilder();
    for (KafkaBrokerInfo brokerInfo : brokerInfos) {
      builder.append(brokerInfo).append(",");
    }
    return builder.toString();
  }

  @Override
  public String toString() {
    return String.format("%s:%d", host, port);
  }
}
