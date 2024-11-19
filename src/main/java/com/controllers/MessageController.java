package com.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MessageDto;
import com.dto.apiResponse.MessageResponseDto;
import com.services.MessageService;

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
public class MessageController {
	private final MessageService messageService;
	
	@Operation(summary = "Create message")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Message created with success", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponseDto.class)) }),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content) })
	@PostMapping("/messages")
	public ResponseEntity<?> addMessage(@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			content = @Content(mediaType = "application/json",
			schema = @Schema(implementation = MessageDto.class),
			examples = @ExampleObject (value = "{ \"message\": \"mon message\", \"user_id\": \"1\", \"rental_id\": \"1\" }")))
	@RequestBody MessageDto messageDto) {
		MessageDto result = messageService.addMessage(messageDto);
		
		if (result == null) {
			return ResponseEntity.status(401).body(null);
		}
		
		return ResponseEntity.ok(new MessageResponseDto("Message send with success"));
	}
}