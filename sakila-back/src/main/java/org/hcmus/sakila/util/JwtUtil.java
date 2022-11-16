package org.hcmus.sakila.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.hcmus.sakila.model.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class JwtUtil {

  @Value("${jwt.secret-key}")
  private String secret;
  private static final long THIRTY_SECONDS = 30_000L;
  private static final long ONE_MINUTE = 60_000L;
  private static final long ONE_HOUR = 3_600_000L;

  private Algorithm setAlgorithm() {
    return Algorithm.HMAC256(secret.getBytes());
  }

  public String generateAccessToken(User user, String issuer) {
    Date expiredDate = new Date(System.currentTimeMillis() + ONE_MINUTE);

    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(expiredDate)
        .withIssuer(issuer)
        .withClaim("roles",
            user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.toList()))
        .sign(setAlgorithm());
  }

  public String generateTokenFromUsername(String username, Role role, String issuer) {
    Date expiredDate = new Date(System.currentTimeMillis() + ONE_MINUTE);

    return JWT.create()
        .withSubject(username)
        .withExpiresAt(expiredDate)
        .withIssuer(issuer)
        .withClaim("roles", Collections.singletonList(role.name()))
        .sign(setAlgorithm());
  }

  public DecodedJWT verifyToken(String token) {
    return JWT.require(setAlgorithm()).build().verify(token);
  }
}
