package com.dto;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RentalDto {
	private int id;
	private String name;
	private float surface;
	private float price;
	private MultipartFile picture;
	private byte[] pictureData;
	private String description;
	private int owner_id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
