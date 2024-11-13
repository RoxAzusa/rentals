package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MessageDto {
	private int id;
	private int rental_id;
	private int user_id;
	private String message;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
