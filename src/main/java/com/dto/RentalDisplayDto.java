package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDisplayDto {
	private String name;
	private float surface;
	private float price;
	private String picture;
	private String description;
	private int ownerId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
