package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDtoWithoutPassword;
import com.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/{idUser}")
	public ResponseEntity<?> getUserById(@PathVariable int idUser) {
		UserDtoWithoutPassword result = userService.getUserById(idUser);
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
}
