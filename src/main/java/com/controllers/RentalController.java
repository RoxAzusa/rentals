package com.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RentalDisplayDto;
import com.dto.RentalDto;
import com.dto.apiResponse.MessageResponseDto;
import com.dto.apiResponse.RentalResponseDto;
import com.services.RentalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {
	private final RentalService rentalService;
	
	@Operation(summary = "Get rentals")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Rentals found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDto.class)) }),
			  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@GetMapping("/rentals")
	public ResponseEntity<?> getRentals() {
		Map<String, List<RentalDisplayDto>> result = rentalService.getRentals();
		
		if (result.isEmpty()) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@Operation(summary = "Get rental by id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Rental found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RentalDisplayDto.class)) }),
			  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@GetMapping("/rentals/{idRental}")
	public ResponseEntity<?> getRentalById(@PathVariable int idRental) {
		RentalDisplayDto result = rentalService.getRentalById(idRental);
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@Operation(summary = "Create rental")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Rental created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponseDto.class))}),
			  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@PostMapping(value = "/rentals")
	public ResponseEntity<?> createRental (@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = RentalDto.class),
			examples = @ExampleObject (value = "{ \"id\": 1, \"name\": \"dream house\", \"surface\": 24, \"price\": 30, \"picture\": \"https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\", \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"owner_id\": 1, \"created_at\": \"2012/12/02\", \"updated_at\": \"2014/12/02\" }")))
	@ModelAttribute RentalDto rentalDto, JwtAuthenticationToken principal) throws IOException {
		int id = Integer.parseInt(principal.getTokenAttributes().get("id").toString());
		RentalDto result = rentalService.createRental(rentalDto, id);
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(new MessageResponseDto("Rental created !"));
	}
	
	@Operation(summary = "Update rental")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Rental updated", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponseDto.class))}),
			  @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content) })
	@PutMapping("/rentals/{idRental}")
	public ResponseEntity<?> updateRental (@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = RentalDto.class),
			examples = @ExampleObject (value = "{ \"id\": 1, \"name\": \"dream house\", \"surface\": 24, \"price\": 30, \"picture\": \"https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg\", \"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", \"owner_id\": 1, \"created_at\": \"2012/12/02\", \"updated_at\": \"2014/12/02\" }")))
	@PathVariable int idRental, @ModelAttribute RentalDto rentalDto) {
		RentalDto result = rentalService.updateRental(idRental, rentalDto);
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(new MessageResponseDto("Rental updated !"));
	}
	
	@Operation(summary = "Get image bytes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Image found", content = @Content(mediaType = "image/jpeg")),
		    @ApiResponse(responseCode = "404", description = "Image not found", content = @Content) })
	@GetMapping("/rentals/image/{code}")
	public byte[] getImageByte(@PathVariable String code) throws IOException {
		return rentalService.findByPicture(code).getPictureData();
	}
}
