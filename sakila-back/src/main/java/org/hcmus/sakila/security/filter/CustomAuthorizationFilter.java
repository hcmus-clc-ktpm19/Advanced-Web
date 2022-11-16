package org.hcmus.sakila.security.filter;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.model.enums.Role;
import org.hcmus.sakila.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (request.getServletPath().equals(Constants.LOGIN_URL) || request.getServletPath().equals(Constants.SIGNIN_URL) || request.getServletPath().equals(Constants.REFRESH_TOKEN_URL)) {
      filterChain.doFilter(request, response);
    } else {
      String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

      if (Objects.nonNull(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
        try {
          String token = authorizationHeader.substring("Bearer ".length());

          DecodedJWT decodedJWT = jwtUtil.verifyToken(token);

          String username = decodedJWT.getSubject();
          Collection<SimpleGrantedAuthority> authorities = new HashSet<>();
          List<Role> roles = decodedJWT.getClaim("roles").asList(Role.class);
          roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.name())));
          UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

          SecurityContextHolder.getContext().setAuthentication(authentication);
          filterChain.doFilter(request, response);
        } catch (TokenExpiredException exception) {
          log.error("Token expired: {}", exception.getMessage());
          filterChain.doFilter(request, response);
        } catch (Exception exception) {
          log.error("Could not parse authentication token", exception);

          response.setHeader("Error", exception.getMessage());
          response.setStatus(FORBIDDEN.value());
          response.setContentType(APPLICATION_JSON_VALUE);

          new ObjectMapper().writeValue(response.getOutputStream(),
              Map.of("Error-Message", exception.getMessage()));
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }
  }
}
