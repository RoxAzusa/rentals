package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.AuthSuccess;
import com.dto.LoginRequestDto;
import com.dto.UserDto;
import com.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService ;
	
	@GetMapping("/me")
	public UserDto getMe() {
		return authService.getMe();		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		String token = authService.login(email, password);
		
		if (token == null) {
			return ResponseEntity.status(401).body("Unauthorized");
		}
		
		return ResponseEntity.ok(new AuthSuccess(token));
	}
	
	@PostMapping("/register")
	public  ResponseEntity<?> register(@RequestBody UserDto user) {
		String token = authService.register(user);
		
		if (token == null) {
			return ResponseEntity.status(401).body("Unauthorized");
		}
		
		return ResponseEntity.ok(new AuthSuccess(token));	
	}
}