package org.hcmus.sakila.security;

import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.model.enums.Role;
import org.hcmus.sakila.security.filter.CustomAuthenticationFilter;
import org.hcmus.sakila.security.filter.CustomAuthorizationFilter;
import org.hcmus.sakila.util.AuthEntryPointJwt;
import org.hcmus.sakila.util.CustomAccessDeniedHandler;
import org.hcmus.sakila.util.JwtUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Qualifier("AccountServiceImpl")
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final AuthEntryPointJwt unauthorizedHandler;

  private final CustomAccessDeniedHandler accessDeniedHandler;

  public WebSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthEntryPointJwt unauthorizedHandler, CustomAccessDeniedHandler accessDeniedHandler) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.unauthorizedHandler = unauthorizedHandler;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter authenticationFilter = new CustomAuthenticationFilter(authenticationManager(), jwtUtil);
    authenticationFilter.setFilterProcessesUrl(Constants.SIGNIN_URL);

    http.cors().and().csrf()
        .disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests()
        .antMatchers(Constants.LOGIN_URL, Constants.REGISTER_URL, Constants.SIGNIN_URL, Constants.REFRESH_TOKEN_URL)
        .permitAll();

//    http.authorizeRequests().antMatchers("/api/v1/films/**")
//        .hasAnyAuthority(Role.FILM.name()).and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    http.authorizeRequests().antMatchers("/api/v1/actors/**")
        .hasAnyAuthority(Role.ACTOR.name()).and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);

    http.authorizeRequests().antMatchers("/api/v1/categories/**")
        .hasAnyAuthority(Role.CATEGORY.name(), Role.ACTOR.name(), Role.FILM.name()).and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);


    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(authenticationFilter).addFilterBefore(new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
  }

}
