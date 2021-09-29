package com.unclezs.sample.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LdapTest {
  public static String ldapUrl = "ldap://test.com:389/";
  public static String ldapAdminName = "test";
  public static String ldapAdminPwd = "test";
  public static String ldapAccountPattern = "CN=${username}";
  public static String ldapSearchFilter = "test";
  public static String ldapBaseDn = "OU=test,DC=test,DC=com";
  public static String ldapMockUser = "test";
  public static String ldapMockUserPwd = "test";
  public static boolean debug = false;

  private static LdapContext getLdapContext() throws NamingException {
    Hashtable<String, String> env = new Hashtable<>();
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.SECURITY_AUTHENTICATION, "simple");
    env.put(Context.PROVIDER_URL, ldapUrl);
    env.put(Context.SECURITY_PRINCIPAL, ldapAdminName);
    env.put(Context.SECURITY_CREDENTIALS, ldapAdminPwd);
    return new InitialLdapContext(env, null);
  }

  public static void findUser() throws NamingException {
    String accountPattern = ldapAccountPattern;
    String accountName = accountPattern.replace("${username}", ldapMockUser);
    LdapContext ldapContext = getLdapContext();
    SearchControls ctls = new SearchControls();
    ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    ctls.setCountLimit(0);
    String searchFilter = ldapSearchFilter.isEmpty() ? accountName : ldapSearchFilter;
    String baseDn = ldapBaseDn;
    NamingEnumeration<SearchResult> results = ldapContext.search(baseDn, searchFilter, ctls);
    while (results.hasMore()) {
      SearchResult next = results.next();
      if (debug) {
        System.out.println(next.getName());
        System.out.println(next.getAttributes().get("sAMAccountName"));
      }
      if (accountName.equals(next.getName()) || accountName.equals(searchFilter)) {
        System.out.println(next.getName());
        ldapContext.addToEnvironment(Context.SECURITY_PRINCIPAL, next.getName() + "," + baseDn);
        ldapContext.addToEnvironment(Context.SECURITY_CREDENTIALS, ldapMockUserPwd);
        ldapContext.reconnect(null);
        ldapContext.close();
        return;
      }
    }
    ldapContext.close();
    throw new NamingException("account not exist from ldap: " + accountName);
  }

  public static void main(String[] args) {
    try {
      findUser();
      System.out.println("成功");
    } catch (NamingException e) {
      e.printStackTrace();
      System.out.println("失败");
    }
  }
}
