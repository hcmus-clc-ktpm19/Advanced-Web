package org.hcmus.ln02.security;

import org.hcmus.ln02.model.enums.Role;
import org.hcmus.ln02.security.filter.CustomAuthenticationFilter;
import org.hcmus.ln02.security.filter.CustomAuthorizationFilter;
import org.hcmus.ln02.util.mapper.AuthEntryPointJwt;
import org.hcmus.ln02.util.mapper.JwtUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Qualifier("AccountServiceImpl")
  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final AuthEntryPointJwt unauthorizedHandler;

  public WebSecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthEntryPointJwt unauthorizedHandler) {
    this.userDetailsService = userDetailsService;
    this.passwordEncoder = passwordEncoder;
    this.jwtUtil = jwtUtil;
    this.unauthorizedHandler = unauthorizedHandler;
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
    authenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");

    http.cors().and().csrf()
        .disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests()
        .antMatchers("/api/v1/auth/login", "/api/v1/auth/register")
        .permitAll();

    http.authorizeRequests().antMatchers("/api/v1/films/**")
        .hasAnyAuthority(Role.FILM.name());

    http.authorizeRequests().antMatchers("api/v1/actors/**")
        .hasAnyAuthority(Role.ACTOR.name());

    http.authorizeRequests().antMatchers("/api/v1/categories/**")
        .hasAnyAuthority(Role.CATEGORY.name());


    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(authenticationFilter).addFilterBefore(new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
  }

}
