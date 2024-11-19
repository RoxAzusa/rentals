package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginRequestDto;
import com.dto.UserDto;
import com.dto.UserDtoWithoutPassword;
import com.dto.apiResponse.AuthSuccess;
import com.dto.apiResponse.MessageResponseDto;
import com.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService ;
	
	@Operation(summary = "Get actual user")
	@ApiResponses(value = {
			  @ApiResponse(responseCode = "200", description = "User found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoWithoutPassword.class)) }),
			  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@GetMapping("/me")
	public ResponseEntity<?> getMe() {
		UserDtoWithoutPassword result = authService.getMe();
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@Operation(summary = "User login with email and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Login success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthSuccess.class))}),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponseDto.class)) }) })
	@PostMapping("/login")
	public ResponseEntity<?> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = LoginRequestDto.class),
			examples = @ExampleObject (value = "{ \"email\": \"test@test.com\", \"password\": \"test!31\" }")))
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		String token = authService.login(email, password);
	@RequestBody LoginRequestDto loginRequest) {
		
		if (token == null) {
			return ResponseEntity.status(401).body(new MessageResponseDto("error"));
		}
		
		return ResponseEntity.ok(new AuthSuccess(token));
	}
	
	@Operation(summary = "User registration")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Register success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AuthSuccess.class)) }),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content) })
	@PostMapping("/register")
	public  ResponseEntity<?> register(@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = UserDto.class),
			examples = @ExampleObject (value = "{ \"email\": \"test@test.com\", \"name\": \"test TEST\", \"password\": \"test!31\" }")))
	@RequestBody UserDto user) {
		String token = authService.register(user);
		
		if (token == null) {
			return ResponseEntity.status(400).body(null);
		}
		
		return ResponseEntity.ok(new AuthSuccess(token));	
	}
}