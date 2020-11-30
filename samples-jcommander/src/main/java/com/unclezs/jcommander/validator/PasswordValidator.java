package com.unclezs.jcommander.validator;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * @author blog.unclezs.com
 * @date 2020/12/1 12:13 上午
 */
public class PasswordValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value)
            throws ParameterException {
        if ("-pwd".equals(name)&&value.length() < 5) {
            System.out.println("password length must > 5,but only " + value.length());
        }
    }
}
