package org.hcmus.sakila.controller;

import lombok.extern.slf4j.Slf4j;
import org.hcmus.sakila.model.dto.UserDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin
public class AuthController {

  @PostMapping("login")
  public String login(@RequestBody UserDto userDto) {
    if (userDto.getUsername().equals("admin") && userDto.getPassword().equals("admin")) {
      return "Login success";
    } else {
      throw new IllegalStateException("Login failed");
    }
  }
}
