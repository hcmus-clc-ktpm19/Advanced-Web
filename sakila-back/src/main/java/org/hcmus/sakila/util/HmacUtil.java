package org.hcmus.sakila.util;

import org.apache.commons.codec.digest.HmacUtils;

public class HmacUtil {
  private final String algorithm;
  private final String secretKey;
  private final String data;

  public HmacUtil(String algorithm, String secretKey, String data) {
    this.algorithm = algorithm;
    this.secretKey = secretKey;
    this.data = data;
  }

  public String hmacWithApacheCommons() {
    return new HmacUtils(algorithm, secretKey).hmacHex(data);
  }

}
