package com.unclezs.test.junit;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * @author blog.unclezs.com
 * @date 2021/07/17
 */
public class AppTest {

  @Test
  public void test() {
    try (MockedStatic<SUtils> mockStatic = Mockito.mockStatic(SUtils.class,
        Mockito.withSettings().defaultAnswer(CALLS_REAL_METHODS))) {
      mockStatic.when(SUtils::a).thenReturn(12);
      System.out.println(SUtils.a());
      System.out.println(SUtils.b());
    }
  }
}
