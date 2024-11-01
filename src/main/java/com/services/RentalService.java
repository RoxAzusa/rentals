package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dto.RentalDto;
import com.models.RentalModel;
import com.repositories.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepository rentalRepository;
	private final ModelMapper modelMapper;
	
	public Map<String, List<RentalDto>> getRentals() {
		List<RentalModel> rentals = rentalRepository.findAll();
		List<RentalDto> rentalsDto = rentals.stream()
				.map(rental -> modelMapper.map(rental, RentalDto.class))
				.collect(Collectors.toList());
		Map<String, List<RentalDto>> response = new HashMap<>();
		response.put("rentals", rentalsDto);
		return response;
	}
}
