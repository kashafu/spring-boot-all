package com.patroclos.springboot3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.patroclos.springboot3.pojo.AuthenticationRequest;
import com.patroclos.springboot3.pojo.AuthenticationResponse;

@Service
public class AuthenticationService {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	public AuthenticationResponse authenticate(AuthenticationRequest request) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtService.generateJwtToken(authentication);

		return AuthenticationResponse.builder().token(jwtToken).build();
	}
}
