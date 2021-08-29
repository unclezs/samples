package com.unclezs.jcommander;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.IStringConverterFactory;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author blog.unclezs.com
 * @date 2020/11/30 11:26 下午
 */
public class ConverterFactorySample {
  @Parameter(names = {"-p"}, description = "person")
  private Person person;

  public static void main(String... argv) {
    ConverterFactorySample target = new ConverterFactorySample();
    String[] args = {"-p", "uncle,2020"};
    JCommander.newBuilder()
        .verbose(1)
        .addObject(target)
        .addConverterFactory(new PersonConverterFactory())
        .build()
        .parse(args);
    System.out.println("person:" + target.person);
  }

  static class Person {
    String name;
    int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return "Person{" +
          "name='" + name + '\'' +
          ", age=" + age +
          '}';
    }
  }


  static class PersonConverter implements IStringConverter<Person> {

    @Override
    public Person convert(String value) {
      String[] pStr = value.split(",");
      return new Person(pStr[0], Integer.parseInt(pStr[1]));
    }
  }


  static class PersonConverterFactory implements IStringConverterFactory {
    @Override
    public Class<? extends IStringConverter<?>> getConverter(Class<?> forType) {
      if (Person.class.equals(forType)) {
        return PersonConverter.class;
      }
      return null;
    }
  }
}
