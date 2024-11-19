package com.dto.apiResponse;

import java.util.List;

import com.dto.RentalDisplayDto;

import lombok.Data;

@Data
public class RentalResponseDto {
	private List<RentalDisplayDto> rentals;
}