package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDisplayDto {
	private int id;
	private String name;
	private float surface;
	private float price;
	private String picture;
	private String description;
	private int owner_id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
