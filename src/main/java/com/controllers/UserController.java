package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDtoWithoutPassword;
import com.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	
	@Operation(summary = "Get user by id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "User found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoWithoutPassword.class)) }),
			  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@GetMapping("/{idUser}")
	public ResponseEntity<?> getUserById(@PathVariable int idUser) {
		UserDtoWithoutPassword result = userService.getUserById(idUser);
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
}
