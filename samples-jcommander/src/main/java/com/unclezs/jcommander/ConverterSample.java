package com.unclezs.jcommander;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;
import java.util.Arrays;

/**
 * 可以使用实现IStringConverter，然后通过converter来实现自定义类型转换
 * 如果是不像在每个注解都写，可以通过实现IStringConverterFactory，在通过jcommander.addConverterFactory方法添加，会通过声明的类型进行判断
 * 如果不想通过声明类型判断，可以实现IStringConverterInstanceFactory来自定定义判断逻辑，通过addConverterInstanceFactory方法注册
 *
 * @author blog.unclezs.com
 * @date 2020/11/30 10:45 下午
 */
public class ConverterSample {

  @Parameter(names = {"-file", "-f"}, description = "file path", converter = FileConverter.class)
  private File file;
  @Parameter(names = {"-i"}, description = "str arr spilt by ,", converter = CustomConverter.class)
  private String[] arr;

  public static void main(String... argv) {
    ConverterSample target = new ConverterSample();
    String[] args =
        {"-file", "/Users/uncle/coder/java/samples/samples-jcommander/src/main/resources/word.txt", "-i", "a,b,c"};
    JCommander.newBuilder()
        .verbose(1)
        .addObject(target)
        .build()
        .parse(args);
    System.out.println("file: " + target.file.getName());
    System.out.println("arr:" + Arrays.toString(target.arr));
  }

  static class CustomConverter implements IStringConverter<String[]> {

    @Override
    public String[] convert(String value) {
      return value.split(",");
    }
  }
}
