package org.hcmus.sakila.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hcmus.sakila.model.dto.AccountDto;
import org.hcmus.sakila.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AccountController extends AbstractApplicationController {

  private final AccountService accountService;

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
  public ResponseEntity<Long> createAccount(@Valid @RequestBody AccountDto accountDto) {
    return ResponseEntity.ok(accountService.saveAccount(accountDto));
  }
}
