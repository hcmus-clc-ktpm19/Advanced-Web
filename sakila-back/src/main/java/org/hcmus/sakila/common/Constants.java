package org.hcmus.sakila.common;

import org.apache.commons.codec.digest.HmacAlgorithms;

public final class Constants {

  public static final String LOGIN_URL = "/api/v1/auth/login";
  public static final String REGISTER_URL = "/api/v1/auth/register";
  public static final String SAKILA_SERVICE_URL = "http://localhost:9090/api/v1";
  public static final String HMAC_ALGORITHMS = HmacAlgorithms.HMAC_SHA_512.toString();

  private Constants() {
  }
}
