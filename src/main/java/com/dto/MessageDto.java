package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {
	private int id;
	private int rentalId;
	private int userId;
	private String message;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
