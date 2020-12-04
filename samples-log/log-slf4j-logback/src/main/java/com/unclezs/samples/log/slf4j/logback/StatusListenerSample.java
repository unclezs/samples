package com.unclezs.samples.log.slf4j.logback;

import ch.qos.logback.core.status.Status;
import ch.qos.logback.core.status.StatusListener;

/**
 * @author blog.unclezs.com
 * @since 2020/12/02 19:44
 */
public class StatusListenerSample implements StatusListener {
  @Override
  public void addStatusEvent(Status status) {
    System.out.println("init: "+status);
  }
}
