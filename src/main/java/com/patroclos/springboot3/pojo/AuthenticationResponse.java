package com.patroclos.springboot3.pojo;

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
