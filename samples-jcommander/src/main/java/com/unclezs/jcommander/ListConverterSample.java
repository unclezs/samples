package com.unclezs.jcommander;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.Arrays;
import java.util.List;

/**
 * 可以使用实现IStringConverter，然后通过converter来实现自定义类型转换
 * 如果是不像在每个注解都写，可以通过实现IStringConverterFactory，在通过jcommander.addConverterFactory方法添加，会通过声明的类型进行判断
 * 如果不想通过声明类型判断，可以实现IStringConverterInstanceFactory来自定定义判断逻辑，通过addConverterInstanceFactory方法注册
 *
 * @author blog.unclezs.com
 * @date 2020/11/30 10:45 下午
 */
public class ListConverterSample {

  @Parameter(names = {"-files", "-f"}, description = "file path", listConverter = CustomListConverter.class)
  private List<String> list;

  public static void main(String... argv) {
    ListConverterSample target = new ListConverterSample();
    String[] args = {"-f", "1", "-f", "2"};
    JCommander.newBuilder()
        .verbose(1)
        .addObject(target)
        .build()
        .parse(args);
    System.out.println("arr:" + target.list);
  }

  static class CustomListConverter implements IStringConverter<List<String>> {

    @Override
    public List<String> convert(String value) {
      return Arrays.asList(value.split(","));
    }
  }
}
