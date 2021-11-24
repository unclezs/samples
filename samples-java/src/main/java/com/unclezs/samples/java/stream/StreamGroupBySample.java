package com.unclezs.samples.java.stream;

import com.unclezs.samples.java.stream.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author blog.unclezs.com
 * @since 2021/11/24 17:32
 */
public class StreamGroupBySample {
  public static void main(String[] args) {
    List<Person> personList = new ArrayList<>();
    personList.add(new Person("a", 0, 1));
    personList.add(new Person("a1", 1, 1));
    personList.add(new Person("a2", 2, 1));
    personList.add(new Person("a3", 3, 1));
    personList.add(new Person("a4", 4, 1));
    personList.add(new Person("a4", 6, 1));
    personList.add(new Person("a4", 5, 1));

    Map<String, Person> personMap =
        personList.stream().collect(Collectors.groupingBy(Person::getName, Collectors.reducing(null, (a, b) -> b)));
    personMap.forEach((k, v) -> System.out.println(k + ": " + v));
  }
}
