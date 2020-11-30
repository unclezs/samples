package com.unclezs.jcommander;


/**
 * @author blog.unclezs.com
 * @date 2020/11/30 9:45 下午
 */
public class PasswordTest {
    public static void main(String[] args) {
        char[] password = System.console().readPassword();
        System.out.println(new String(password));
    }
}
