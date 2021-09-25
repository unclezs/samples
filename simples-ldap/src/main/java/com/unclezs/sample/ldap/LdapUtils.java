package com.unclezs.sample.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import java.util.Hashtable;

/**
 * @author blog.unclezs.com
 * @date 2021/9/25
 */
public class LdapUtils {
  public static void main(String[] args) {
    String baseDn = "OU=后端研发,DC=unclezs,DC=com";
    try {
      Hashtable<String, String> env = new Hashtable<>();
      env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
      env.put(Context.SECURITY_AUTHENTICATION, "simple");
      env.put(Context.PROVIDER_URL, "ldap://192.168.220.128:389/");
      env.put(Context.SECURITY_PRINCIPAL, "CN=admin,CN=Users,DC=unclezs,DC=com");
      env.put(Context.SECURITY_CREDENTIALS, "Abc123.");
      LdapContext context = new InitialLdapContext(env, null);
      SearchControls searchControls = new SearchControls();
      searchControls.setCountLimit(0);
      searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
      NamingEnumeration<SearchResult> results =
          context.search(baseDn, "(cn=lisi)", searchControls);
      while (results.hasMore()) {
        SearchResult next = results.next();
        System.out.println("结果： " + next.getName());
        context.addToEnvironment(Context.SECURITY_PRINCIPAL, next.getName() + "," + baseDn);
        context.addToEnvironment(Context.SECURITY_CREDENTIALS, "Abc123.");
        context.reconnect(null);
        context.close();
      }
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
