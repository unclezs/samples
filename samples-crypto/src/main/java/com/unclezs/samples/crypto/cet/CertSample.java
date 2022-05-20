package com.unclezs.samples.crypto.cet;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.bouncycastle.util.encoders.Hex;
import sun.security.rsa.RSAPublicKeyImpl;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;

import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;

/**
 * @author blog.unclezs.com
 * @since 2022/5/19 15:25
 */
public class CertSample {
  public static void main(String[] args) throws Exception {
    URL url = new URL("https://blog.unclezs.com");
    HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
    connection.connect();
    Certificate[] certs = connection.getServerCertificates();
    int level = certs.length;
    for (Certificate certificate : certs) {
      System.out.printf("==========第 %s 级证书=========%n", level--);
      JSONObject info = new JSONObject();
      // 第一个就是服务器本身证书，后续的是证书链上的其他证书
      X509CertImpl cert = (X509CertImpl) certificate;
      info.set("版本", cert.getVersion());
      info.set("序列号", cert.getSerialNumber());
      info.set("签名算法", cert.getSigAlgName());
      info.set("在此之前无效", DateUtil.formatDateTime(cert.getNotBefore()));
      info.set("在此之后无效", DateUtil.formatDateTime(cert.getNotAfter()));
      info.set("公钥", Base64.encode(cert.getPublicKey().getEncoded()));
      info.set("公钥算法", cert.getPublicKey().getAlgorithm());
      if ("RSA".equals(cert.getPublicKey().getAlgorithm())) {
        info.set("公钥长度", ((RSAPublicKeyImpl) cert.getPublicKey()).getModulus().bitLength());
      }
      JSONObject subject = new JSONObject();
      info.set("主体", subject);
      fillX500Name(subject, X500Name.asX500Name(cert.getSubjectX500Principal()));
      JSONObject issuer = new JSONObject();
      info.set("签发者", issuer);
      fillX500Name(issuer, X500Name.asX500Name(cert.getIssuerX500Principal()));
      JSONObject ext = new JSONObject();
      info.set("扩展字段", ext);
      // https://www.alvestrand.no/objectid/2.5.29.html
      ext.set("密匙使用", Hex.toHexString(cert.getExtensionValue("2.5.29.15")));
      ext.set("基本约束", Hex.toHexString(cert.getExtensionValue("2.5.29.19")));
      ext.set("主题密钥标识符", Hex.toHexString(cert.getExtensionValue("2.5.29.14")));
      ext.set("授权密钥标识符", Hex.toHexString(cert.getExtensionValue("2.5.29.35")));
      System.out.println(JSONUtil.toJsonPrettyStr(info));
    }
    connection.disconnect();
  }

  private static void fillX500Name(JSONObject obj, X500Name x500Name) throws IOException {
    obj.set("名称", x500Name.getName());
    obj.set("国家", x500Name.getCountry());
    obj.set("组织", x500Name.getOrganization());
    obj.set("组织单位", x500Name.getOrganizationalUnit());
    obj.set("状态", x500Name.getState());
    obj.set("常用名称", x500Name.getCommonName());
    obj.set("主体", x500Name.getDomain());
    obj.set("缩写", x500Name.getInitials());
    obj.set("IP", x500Name.getIP());
    obj.set("姓名", x500Name.getSurname());
    obj.set("类型", x500Name.getType());
    obj.set("位置", x500Name.getLocality());
  }
}
