package com.patroclos.springboot3.controller;

import com.patroclos.springboot3.models.User;
import com.patroclos.springboot3.payload.request.SignupRequest;
import com.patroclos.springboot3.payload.response.MessageResponse;
import com.patroclos.springboot3.pojo.AuthenticationRequest;
import com.patroclos.springboot3.pojo.AuthenticationResponse;
import com.patroclos.springboot3.repository.UserRepository;
import com.patroclos.springboot3.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  @Autowired
  UserRepository userRepository;
  @Autowired
  PasswordEncoder encoder;
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
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }


  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()));

//    Set<String> strRoles = signUpRequest.getRoles();
//    Set<Role> roles = new HashSet<>();
//
//    if (strRoles == null) {
//      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//      roles.add(userRole);
//    } else {
//      strRoles.forEach(role -> {
//        switch (role) {
//          case "admin":
//            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(adminRole);
//
//            break;
//          case "mod":
//            Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(modRole);
//
//            break;
//          default:
//            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//            roles.add(userRole);
//        }
//      });
//    }
//
//    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

}