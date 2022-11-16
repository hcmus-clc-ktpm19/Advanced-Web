package org.hcmus.ln02.security;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.hcmus.ln02.security.filter.APIKeyAuthFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
@Order(1)
@SuppressWarnings("unchecked")
public class APISecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${system-auth.http.auth-header-name}")
  private String principalRequestHeader;

  @Value("${system-auth.http.time-header-name}")
  private String timeRequestHeader;

  @Value("${system-auth.http.secret-key}")
  private String principalRequestValue;

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    APIKeyAuthFilter filter = new APIKeyAuthFilter(principalRequestHeader, timeRequestHeader);
    filter.setAuthenticationManager(authentication -> {
      ArrayList<String> credentials = (ArrayList<String>) authentication.getCredentials();
      LocalDateTime credentialsTime = LocalDateTime.parse(credentials.get(1));
      if (LocalDateTime.now().isAfter(credentialsTime.plusMinutes(1))) {
        throw new BadCredentialsException("Time is expired");
      }
      String principal = (String) authentication.getPrincipal();
      if (!principal.equals(hmacWithApacheCommons(HmacAlgorithms.HMAC_SHA_512.toString(), credentials.get(0) + credentials.get(1), principalRequestValue))) {
        throw new BadCredentialsException("The API key was not found or not the expected value");
      }
      authentication.setAuthenticated(true);
      return authentication;
    });
    httpSecurity.
        antMatcher("/api/**").
        csrf().disable().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
  }

  public static String hmacWithApacheCommons(String algorithm, String data, String key) {
    return new HmacUtils(algorithm, key).hmacHex(data);
  }

}