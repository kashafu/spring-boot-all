package com.spring.app.payload.response;

import lombok.Builder;

@Builder
public class AuthenticationResponse {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
