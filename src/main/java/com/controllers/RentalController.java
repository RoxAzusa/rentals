package com.controllers;

import java.util.List;
import java.util.Map;

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
	public Map<String, List<RentalDto>> getRentals() {
		return rentalService.getRentals();
	}
}
