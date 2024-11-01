package com.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentalDto {
	private int id;
	private String name;
	private float surface;
	private float price;
	private String picture;
	private byte[] pictureData;
	private String description;
	private int ownerId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
