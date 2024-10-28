package com.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/register")
	public String register(@RequestBody UserModel user) {
		System.out.println(user);
		return "";
	}
}