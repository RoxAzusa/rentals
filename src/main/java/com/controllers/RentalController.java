package com.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RentalDto;
import com.services.RentalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {
	private final RentalService rentalService;
	
	@GetMapping("/rentals")
	public ResponseEntity<?> getRentals() {
		Map<String, List<RentalDto>> result = rentalService.getRentals();
		if (result.isEmpty()) {
			return ResponseEntity.status(401).body("Unauthorized");
		}
		
		return ResponseEntity.ok(result);
	}
}
