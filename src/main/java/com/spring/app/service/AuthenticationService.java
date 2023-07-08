package com.spring.app.service;

import com.spring.app.models.User;
import com.spring.app.payload.request.SignupRequest;
import com.spring.app.payload.response.MessageResponse;
import com.spring.app.payload.request.AuthenticationRequest;
import com.spring.app.payload.response.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	public ResponseEntity<?> authenticate(AuthenticationRequest request) {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = jwtService.generateJwtToken(authentication);

			return ResponseEntity.ok(AuthenticationResponse.builder().token(jwtToken).build());
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}
	}

	public ResponseEntity<?> register(SignupRequest request) {

		try {
			if (userService.existsByUsername(request.getUsername())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
			}

			if (userService.existsByEmail(request.getEmail())) {
				return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
			}

			User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
			userService.createUser(user);
			return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		}
		catch (Exception e){
			return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
		}
	}
}
