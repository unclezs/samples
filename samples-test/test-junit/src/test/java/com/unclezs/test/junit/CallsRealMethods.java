package com.unclezs.test.junit;

import static org.mockito.Mockito.RETURNS_DEFAULTS;
import static org.mockito.internal.exceptions.Reporter.cannotCallAbstractRealMethod;

import org.mockito.internal.stubbing.answers.InvocationInfo;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.ValidableAnswer;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CallsRealMethods implements Answer<Object>, ValidableAnswer, Serializable {
  private static final long serialVersionUID = 9057165148930624087L;

  @Override
  public Object answer(InvocationOnMock invocation) throws Throwable {
    if (Modifier.isAbstract(invocation.getMethod().getModifiers())) {
      return RETURNS_DEFAULTS.answer(invocation);
    }

    Method method = invocation.getMethod();
    return invocation.callRealMethod();
  }

  @Override
  public void validateFor(InvocationOnMock invocation) {
    if (new InvocationInfo(invocation).isAbstract()) {
      throw cannotCallAbstractRealMethod();
    }
  }
}
