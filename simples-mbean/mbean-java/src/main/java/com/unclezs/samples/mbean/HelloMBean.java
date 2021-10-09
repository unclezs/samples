package com.unclezs.samples.mbean;

import com.unclezs.samples.mbean.mbeans.Car;

import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @author blog.unclezs.com
 * @since 2021/10/8 17:26
 */
public class HelloMBean {

  public static void main(String[] args) throws Exception {
    MBeanServer server =
        ManagementFactory.getPlatformMBeanServer();
    server.registerMBean(new Car(), new ObjectName("jmxBean:name=car"));
    //这个步骤很重要，注册一个端口，绑定url后用于客户端通过rmi方式连接JMXConnectorServer
    LocateRegistry.createRegistry(9999);
    //URL路径的结尾可以随意指定，但如果需要用Jconsole来进行连接，则必须使用jmxrmi
    JMXServiceURL url = new JMXServiceURL
        ("service:jmx:rmi:///jndi/rmi://localhost:9999/jmxrmi");
    JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(url, null, server);
    System.out.println("begin rmi start");
    jcs.start();
    System.out.println("rmi start");
    Thread.currentThread().join();
  }
}
