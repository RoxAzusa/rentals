package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private String email;
	private String name;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;	
}
