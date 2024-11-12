package com.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name ="rentals")
@Data
public class RentalModel {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surface")
	private float surface;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "picture")
	private String picture;
	
	@Lob
	@Column(name = "picture_data")
	private byte[] pictureData;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "owner_id")
	private int owner_id;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
}
