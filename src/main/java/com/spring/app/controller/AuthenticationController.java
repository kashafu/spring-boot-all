package com.spring.app.controller;

import com.spring.app.models.User;
import com.spring.app.payload.request.SignupRequest;
import com.spring.app.payload.request.AuthenticationRequest;
import com.spring.app.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthenticationController {

  @Autowired
  private AuthenticationService service;

  @Operation(summary = "Authenticate to get access JWT token")
  @ApiResponses(value = { 
    @ApiResponse(responseCode = "200", description = "Success Authentication", 
      content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = User.class)) }),
    @ApiResponse(responseCode = "400", description = "Failed Authentication", 
      content = @Content), 
    @ApiResponse(responseCode = "404", description = "Failed Authentication", 
      content = @Content) })
  @PostMapping("/authenticate")
  public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
    return service.authenticate(request);
  }


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return service.register(signUpRequest);
  }

}