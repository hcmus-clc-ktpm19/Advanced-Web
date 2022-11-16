package org.hcmus.sakila.config;

import org.hcmus.sakila.util.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class SakilaConfiguration {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CustomAccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
}
