package com.dto.apiResponse;

import lombok.Data;

@Data
public class AuthSuccess {
	private String token;
	
	public AuthSuccess(String token) {
		this.token = token;
	}
}
