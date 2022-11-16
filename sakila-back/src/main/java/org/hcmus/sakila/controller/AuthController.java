package org.hcmus.sakila.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hcmus.sakila.common.Constants;
import org.hcmus.sakila.model.dto.AccountDto;
import org.hcmus.sakila.model.dto.JwtResponse;
import org.hcmus.sakila.model.dto.UserDto;
import org.hcmus.sakila.model.entity.Account;
import org.hcmus.sakila.model.entity.RefreshToken;
import org.hcmus.sakila.service.AccountService;
import org.hcmus.sakila.service.RefreshTokenService;
import org.hcmus.sakila.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {

  private final AccountService accountService;

  private final AuthenticationManager authenticationManager;

  private final JwtUtil jwtUtil;

  private final RefreshTokenService refreshTokenService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody UserDto userDto) {
    try {
      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(),
              userDto.getPassword()));
      User user = (User) authentication.getPrincipal();
      String accessToken = jwtUtil.generateAccessToken(user, Constants.FULL_LOGIN_URL);
      RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDto.getUsername());
      Account account = accountService.findAccountByUsername(user.getUsername());
      return ResponseEntity.ok(
          new JwtResponse(accessToken, refreshToken.getToken(), account.getId(), user.getUsername(),
              account.getRole().toString()));
    } catch (Exception e) {
      log.error("Error when login", e);
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HashMap<String, String>() {{
        put("message", "Invalid username or password");
      }});
    }
  }

  @Operation(
      summary = "Save new account",
      description = "Input Username, Password and Role for saving new account, id will be generated automatically."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201",
          content = @Content(mediaType = "text/plain", schema = @Schema(implementation = AccountDto.class))
      ),
  })
  @PostMapping("/register")
  public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto accountDto) {
    HashMap<String, String> map = new HashMap<>();
    try {
      String id = accountService.saveAccount(accountDto);
      map.put("message", "Account created successfully");
      map.put("id", id);
      map.put("username", accountDto.getUsername());
      return new ResponseEntity<>(map, HttpStatus.CREATED);
    } catch (Exception e) {
      map.put("message", "Account created failed");
      return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
  }
}
