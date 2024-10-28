package com.dto;

import lombok.Data;

@Data
public class AuthSuccess {
	private String token;
	
	public AuthSuccess(String token) {
		this.token = token;
	}
}
