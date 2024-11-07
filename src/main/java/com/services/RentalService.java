package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.dto.RentalDisplayDto;
import com.dto.RentalDto;
import com.models.RentalModel;
import com.repositories.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RentalService {
	private final RentalRepository rentalRepository;
	private final ModelMapper modelMapper;
	private static String chars = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ123456789";
	private static int charLength = chars.length();
	
	public String generateRandomCode(final int size) {
		StringBuilder pass = new StringBuilder(7);
		
		for (int x = 0; x < size; x++) {
			int i = new Random().nextInt(charLength);
			pass.append(chars.charAt(i));
		}
		
		return pass.toString();
	}
	
	public Map<String, List<RentalDisplayDto>> getRentals() {
		List<RentalModel> rentals = rentalRepository.findAll();
		List<RentalDisplayDto> rentalsDto = rentals.stream()
				.map(rental -> modelMapper.map(rental, RentalDisplayDto.class))
				.collect(Collectors.toList());
		rentalsDto.stream().forEach(rental -> rental.setPicture("http://localhost:8080/api/rentals/image/" + rental.getPicture()));
		Map<String, List<RentalDisplayDto>> response = new HashMap<>();
		response.put("rentals", rentalsDto);
		return response;
	}
	
	public RentalDto getRentalById(int idRental) {
		Optional<RentalModel> rental = rentalRepository.findById(idRental);
		return modelMapper.map(rental, RentalDto.class);
	}
	
	public RentalDto findByPicture(String picture) {
		Optional<RentalModel> rental = rentalRepository.findByPicture(picture);
		return modelMapper.map(rental.get(), RentalDto.class);
	}
}
