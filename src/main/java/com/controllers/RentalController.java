package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RentalDisplayDto;
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
		Map<String, List<RentalDisplayDto>> result = rentalService.getRentals();
		if (result.isEmpty()) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/rentals/{idRental}")
	public ResponseEntity<?> getRentalById(@PathVariable int idRental) {
		RentalDto result = rentalService.getRentalById(idRental);
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/rentals")
	public ResponseEntity<?> createRental (@ModelAttribute RentalDto rentalDto) throws IOException {
		RentalDto result = rentalService.createRental(rentalDto);
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok("Rental created !");
	}
	
	@PutMapping("/rentals/{idRental}")
	public ResponseEntity<?> updateRental (@PathVariable int idRental, @ModelAttribute RentalDto rentalDto) {
		RentalDto result = rentalService.updateRental(idRental, rentalDto);
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok("Rental updated !");
	}
	
	@GetMapping("/rentals/image/{code}")
	public byte[] getImageByte(@PathVariable String code) throws IOException {
		return rentalService.findByPicture(code).getPictureData();
	}
}
