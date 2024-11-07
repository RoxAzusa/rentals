package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDtoWithoutPassword {
	private int id;
	private String email;
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;	
}
