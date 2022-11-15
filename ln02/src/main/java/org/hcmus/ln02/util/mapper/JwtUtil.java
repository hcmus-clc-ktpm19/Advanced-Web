package org.hcmus.ln02.util.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
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
  private static final long ONE_HOURS = 3_600_000L;

  private Algorithm setAlgorithm() {
    return Algorithm.HMAC256(secret.getBytes());
  }

  public String generateAccessToken(User user, String issuer) {
    Date expiredDate = new Date(System.currentTimeMillis() + ONE_HOURS);

    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(expiredDate)
        .withIssuer(issuer)
        .withClaim("roles",
            user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.toList()))
        .sign(setAlgorithm());
  }

  public String generateRefreshToken(String username, String issuer) {
    Date refreshTokenExpiredDate = new Date(System.currentTimeMillis() + ONE_HOURS);

    return JWT.create()
        .withSubject(username)
        .withExpiresAt(refreshTokenExpiredDate)
        .withIssuer(issuer)
        .sign(setAlgorithm());
  }

  public DecodedJWT verifyToken(String token) {
    return JWT.require(setAlgorithm()).build().verify(token);
  }
}
